package com.prompthub.prompt.controller;

import com.prompthub.prompt.model.dto.PromptDTO;
import com.prompthub.prompt.service.PromptService;
import com.prompthub.user.model.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prompts")
public class PromptController {

    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }


    @PostMapping("/create")
    public int createPrompt(PromptDTO prompt, HttpSession session) {
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if(loginUser == null) {
            throw new RuntimeException("로그인 필요");
        }

        prompt.setUserId(loginUser.getUserId());

        return promptService.insertPrompt(prompt);
    }


    @GetMapping("/list")
    public List<PromptDTO> selectAllPrompts() {
        return promptService.selectAllPrompts();
    }


    @GetMapping("/detail")
    public PromptDTO selectPromptById(
            @RequestParam("id") Long promptId, HttpSession session) {

        PromptDTO dto = promptService.selectPromptById(promptId);

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        Long loginUserId = loginUser != null ? loginUser.getUserId() : null;

        if (loginUserId == null || !dto.getUserId().equals(loginUserId)) {
            promptService.increaseViewCount(promptId);

            dto = promptService.selectPromptById(promptId);
        }

        return dto;
    }


    @PostMapping("/update")
    public int updatePrompt(PromptDTO prompt, HttpSession session) {

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if(loginUser == null){
            throw new RuntimeException("로그인 필요");
        }

        PromptDTO original = promptService.selectPromptById(prompt.getPromptId());

        if(!original.getUserId().equals(loginUser.getUserId())){
            throw new RuntimeException("수정 권한 없음");
        }

        return promptService.updatePrompt(prompt);
    }


    @PostMapping("/delete")
    public int deletePrompt(
            @RequestParam Long promptId, HttpSession session) {

        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        if(loginUser == null){
            throw new RuntimeException("로그인 필요");
        }

        PromptDTO original = promptService.selectPromptById(promptId);

        if(!original.getUserId().equals(loginUser.getUserId())) {
            throw new RuntimeException("삭제 권한 없음");
        }

        return promptService.deletePrompt(promptId);
    }

}
