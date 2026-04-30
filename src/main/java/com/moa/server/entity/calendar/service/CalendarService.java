package com.moa.server.entity.calendar.service;

import com.moa.server.common.FileUtil;
import com.moa.server.common.exception.CustomException;
import com.moa.server.common.exception.ErrorCode;
import com.moa.server.entity.calendar.*;
import com.moa.server.entity.calendar.dto.CalendarCategoryResponseDTO;
import com.moa.server.entity.calendar.dto.CalendarRequestDTO;
import com.moa.server.entity.calendar.dto.CalendarResponseDTO;
import com.moa.server.entity.user.UserRepository;
import com.moa.server.entity.user.dto.SessionUser;
import com.moa.server.entity.user.dto.UserResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;
    private final CalendarCategoryRepository calendarCategoryRepository;
    private final CalendarRoleRepository calendarRoleRepository;
    private final FileUtil fileUtil;

    private SessionUser getLoginUser(HttpSession session) {
        SessionUser loginUser = (SessionUser) session.getAttribute(SessionUser.USER);
        if (loginUser == null) throw new CustomException(ErrorCode.UNAUTHORIZED);
        return loginUser;
    }

    private String formatDate(java.time.LocalDateTime date){
        if(date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    private LocalDateTime parseDate(String date) {
        if (date == null) return null;
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    private CalendarResponseDTO toDTO(CalendarEntity c) {
        return CalendarResponseDTO.builder()
                .calendarId(c.getCalendarId())
                .writer(c.getWriter())
                .type(c.getType())
                .calendarCategoryId(c.getCalendarCategoryId())
                .calendarCategoryName(c.getCalendarCategory() != null ?
                        c.getCalendarCategory().getCalendarCategoryName() : null)
                .eventStartDate(formatDate(c.getEventStartDate()))
                .eventEndDate(formatDate(c.getEventEndDate()))
                .eventTitle(c.getEventTitle())
                .eventContent(c.getEventContent())
                .file(c.getFile())
                .alarm(c.getAlarm())
                .writerName(c.getWriterUser() != null ? c.getWriterUser().getUserName() : null)
                .sharedUserIds(
                        calendarRoleRepository.findByCalendarId(c.getCalendarId())
                                .stream()
                                .map(CalendarRoleEntity::getUserId)
                                .toList()
                )
                .build();
    }


    public List<CalendarResponseDTO> getCalendars(String type, HttpSession session){

        SessionUser loginUser = getLoginUser(session);
        //공유캘린더 목록
        List<Integer> sharedCalendarIds = calendarRoleRepository
                .findByUserId(loginUser.getUserId())
                .stream()
                .map(CalendarRoleEntity::getCalendarId)
                .toList();

        List<CalendarEntity> calendars;
        if("공유".equals(type)){
            //공유일정만
            calendars = calendarRepository.findByCalendarIdIn(sharedCalendarIds);
        } else if ("개인".equals(type)) {
            //개인일정만
            calendars = calendarRepository.findByWriterAndType(loginUser.getUserId(),"개인");
        }else{
            //전체
            if (sharedCalendarIds.isEmpty()) {
                calendars = calendarRepository.findByWriter(loginUser.getUserId());
            } else {
                calendars = calendarRepository.findByWriterOrCalendarIdIn(loginUser.getUserId(), sharedCalendarIds);
            }
        }
        return calendars.stream()
                .map(this::toDTO)
                .toList();
    }

    //상세조회
    public CalendarResponseDTO getCalendar(Integer calendarId, HttpSession session){

        getLoginUser(session);

        CalendarEntity calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.CALENDAR_NOT_FOUND));

        return toDTO(calendar);
    }

    //일정구분 조회
    public List<CalendarCategoryResponseDTO> getCategories(){

        return calendarCategoryRepository.findAll()
                .stream()
                .map(c -> CalendarCategoryResponseDTO.builder()
                        .calendarCategoryId(c.getCalendarCategoryId())
                        .calendarCategoryName(c.getCalendarCategoryName())
                        .build())
                .toList();
    }

    //공유자 조회(모달창에서)
    public List<UserResponseDTO> getMembers(HttpSession session){

        SessionUser loginUser = getLoginUser(session);

        return userRepository.findAll()
                .stream()
                //본인을 목록에서 제외
                .filter(u -> !u.getUserId().equals(loginUser.getUserId()))
                .map( u -> UserResponseDTO.builder()
                        .userId(u.getUserId())
                        .userName(u.getUserName())
                        .departmentName(u.getDepartment().getDepartmentName())
                        .build())
                .toList();
    }

    //캘린더 등록
    public void saveCalendar(CalendarRequestDTO request, MultipartFile file, HttpSession session) throws IOException {
        SessionUser loginUser = getLoginUser(session);

        String fileName = null;
        if (file != null && !file.isEmpty()) {
            fileName = fileUtil.saveFile(file);
        }

        CalendarEntity calendar = CalendarEntity.builder()
                .writer(loginUser.getUserId())
                .type(request.getType())
                .calendarCategoryId(request.getCalendarCategoryId())
                .eventStartDate(parseDate(request.getEventStartDate()))
                .eventEndDate(parseDate(request.getEventEndDate()))
                .eventTitle(request.getEventTitle())
                .eventContent(request.getEventContent())
                .alarm(request.getAlarm())
                .file(fileName)
                .build();

        CalendarEntity saved = calendarRepository.save(calendar);

        if (request.getSharedUserIds() != null && !request.getSharedUserIds().isEmpty()) {
            request.getSharedUserIds().forEach(userId -> {
                CalendarRoleEntity role = CalendarRoleEntity.builder()
                        .calendarId(saved.getCalendarId())
                        .userId(userId)
                        .build();
                calendarRoleRepository.save(role);
            });
        }
    }

    //캘린더 수정
    public void updateCalendar(Integer calendarId, CalendarRequestDTO request, MultipartFile file, HttpSession session) throws IOException {
        SessionUser loginUser = getLoginUser(session);

        CalendarEntity calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.CALENDAR_NOT_FOUND));

        if (!calendar.getWriter().equals(loginUser.getUserId())) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }


        final String fileName;

        if (file != null && !file.isEmpty()) {
            fileUtil.deleteFile(calendar.getFile());
            fileName = fileUtil.saveFile(file);
        } else {
            fileName = calendar.getFile();
        }

        calendar.setType(request.getType());
        calendar.setCalendarCategoryId(request.getCalendarCategoryId());
        calendar.setEventStartDate(parseDate(request.getEventStartDate()));
        calendar.setEventEndDate(parseDate(request.getEventEndDate()));
        calendar.setEventTitle(request.getEventTitle());
        calendar.setEventContent(request.getEventContent());
        calendar.setAlarm(request.getAlarm());
        calendar.setFile(fileName);

        calendarRoleRepository.deleteByCalendarId(calendarId);

        if (request.getSharedUserIds() != null && !request.getSharedUserIds().isEmpty()) {
            request.getSharedUserIds().forEach(userId -> {
                CalendarRoleEntity role = CalendarRoleEntity.builder()
                        .calendarId(calendarId)
                        .userId(userId)
                        .build();
                calendarRoleRepository.save(role);
            });
        }
    }

    // 캘린더 삭제
    public void deleteCalendar(Integer calendarId, HttpSession session) {
        SessionUser loginUser = getLoginUser(session);
        CalendarEntity calendar = calendarRepository.findById(calendarId)
                .orElseThrow(() -> new CustomException(ErrorCode.CALENDAR_NOT_FOUND));

        if (!calendar.getWriter().equals(loginUser.getUserId())) {
            throw new CustomException(ErrorCode.FORBIDDEN);
        }

        fileUtil.deleteFile(calendar.getFile());

        calendarRoleRepository.deleteByCalendarId(calendarId);
        calendarRepository.delete(calendar);
    }
}


