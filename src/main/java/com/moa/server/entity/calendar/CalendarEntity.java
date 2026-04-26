package com.moa.server.entity.calendar;

import com.moa.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar")
@Getter
@Setter // DTO 역할을 위해 세터를 열어줍니다
@NoArgsConstructor // JPA와 DTO 처리를 위한 기본 생성자
@AllArgsConstructor
@Builder
public class CalendarEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calender_id")
    private Integer calendarId;

    @Column(name = "writer")
    private Integer writer;

    @Column(name = "type")
    private String type;

    @Column(name = "calendar_category_id")
    private Integer calendarCategoryId;

    @Column(name = "event_start_date")
    private LocalDateTime eventStartDate;

    @Column(name = "event_end_date")
    private LocalDateTime eventEndDate;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "file")
    private String file;

    @Column(name = "alarm")
    private Integer alarm;

    @ManyToOne
    @JoinColumn(name = "calendar_category_id", insertable = false, updatable = false)
    private CalendarCategoryEntity calendarCategory;
}
