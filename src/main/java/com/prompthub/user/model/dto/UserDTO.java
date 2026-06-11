package com.prompthub.user.model.dto;

public class UserDTO {

    private Long userId;
    private String loginId;
    private String passwordHash;
    private String nickname;

    public UserDTO() {
    }

    public UserDTO(Long userId, String loginId, String passwordHash, String nickname) {
        this.userId = userId;
        this.loginId = loginId;
        this.passwordHash = passwordHash;
        this.nickname = nickname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
