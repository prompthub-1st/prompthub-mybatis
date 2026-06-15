package com.prompthub.user.controller;

import com.prompthub.user.model.dto.LoginRequest;
import com.prompthub.user.model.dto.UserDTO;
import com.prompthub.user.model.dto.UserResponse;
import com.prompthub.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request,
                                     HttpSession session) {

        UserDTO user = userService.login(
                request.getLoginId(),
                request.getPassword());

        if(user == null){
            throw new RuntimeException("로그인 실패");
        }

        session.setAttribute("loginUser", user);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", user);
        result.put("message", "로그인 성공");

        return result;
    }

    @GetMapping("/me")
    public Map<String, Object> me(HttpSession session) {

        UserDTO user = (UserDTO) session.getAttribute("loginUser");

        if (user == null) {
            Map<String, Object> res = new HashMap<>();
            res.put("success", false);
            res.put("message", "로그인 상태가 아닙니다");
            res.put("data", null);
            return res;
        }

        UserResponse response = new UserResponse();
        response.setUserId(user.getUserId());
        response.setLoginId(user.getLoginId());
        response.setNickname(user.getNickname());

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("data", response);
        res.put("message", "OK");

        return res;
    }

    @PostMapping("/logout")
    public Map<String, Object> logout(HttpSession session){
        session.invalidate();

        return Map.of(
                "success", true,
                "message", "로그아웃 성공"
        );
    }
}
