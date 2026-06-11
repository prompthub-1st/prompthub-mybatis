package com.prompthub.prompt.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PromptDTO {

    private Long promptId;

    private Long userId;
    private Long categoryId;

    private String title;
    private String description;
    private String content;

    private int viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
