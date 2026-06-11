package com.prompthub.prompt.service;

import com.prompthub.prompt.model.dao.PromptMapper;
import com.prompthub.prompt.model.dto.PromptDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptService {

    private final PromptMapper promptMapper;

    public PromptService(PromptMapper promptMapper) {
        this.promptMapper = promptMapper;
    }

    public List<PromptDTO> selectAllPrompts() {
        return promptMapper.selectAllPrompts();
    }

    public PromptDTO selectPromptById(Long promptId) {

        promptMapper.increaseViewCount(promptId);

        return promptMapper.selectPromptById(promptId);
    }

    public int insertPrompt(PromptDTO prompt) {
        return promptMapper.insertPrompt(prompt);
    }

    public int updatePrompt(PromptDTO prompt) {
        return promptMapper.updatePrompt(prompt);
    }

    public int deletePrompt(Long promptId) {
        return promptMapper.deletePrompt(promptId);
    }
}
