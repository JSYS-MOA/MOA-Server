package com.moa.server.entity.notice.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponseDTO {

    private Integer noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeType;
    private String file;
    private String postDate;
    private String writerName;
}
