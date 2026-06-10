package com.prompthub.prompt.model.dao;

import com.prompthub.prompt.model.dto.PromptDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PromptMapper {

    List<PromptDTO> selectAllPrompts();

    PromptDTO selectPromptById(@Param("promptId") Long promptId);

    int insertPrompt(PromptDTO prompt);

    int updatePrompt(PromptDTO prompt);

    int deletePrompt(@Param("promptId") Long promptId);

    int increaseViewCount(@Param("promptId") Long promptId);
}
