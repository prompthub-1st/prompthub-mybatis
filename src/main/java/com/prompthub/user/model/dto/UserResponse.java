package com.prompthub.user.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long userId;
    private String loginId;
    private String nickname;
}
