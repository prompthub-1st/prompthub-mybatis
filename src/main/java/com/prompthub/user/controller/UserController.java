package com.prompthub.user.controller;

import com.prompthub.user.model.dto.LoginRequest;
import com.prompthub.user.model.dto.UserDTO;
import com.prompthub.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
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
        result.put("message", "로그인 성공");
        result.put("user", user);

        return result;
    }

    @GetMapping("/me")
    public Object me(HttpSession session) {
        return session.getAttribute("loginUser");
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
