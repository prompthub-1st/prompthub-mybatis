package com.prompthub.prompt.controller;

import com.prompthub.prompt.model.dto.PromptDTO;
import com.prompthub.prompt.service.PromptService;
import lombok.RequiredArgsConstructor;
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
    public int createPrompt(PromptDTO prompt) {
        return promptService.insertPrompt(prompt);
    }


    @GetMapping("/list")
    public List<PromptDTO> selectAllPrompts() {
        return promptService.selectAllPrompts();
    }


    @GetMapping("/detail")
    public PromptDTO selectPromptById(
            @RequestParam("id") Long promptId) {

        return promptService.selectPromptById(promptId);
    }


    @PostMapping("/update")
    public int updatePrompt(PromptDTO prompt) {

        return promptService.updatePrompt(prompt);
    }


    @PostMapping("/delete")
    public int deletePrompt(
            @RequestParam Long promptId) {

        return promptService.deletePrompt(promptId);
    }
}
