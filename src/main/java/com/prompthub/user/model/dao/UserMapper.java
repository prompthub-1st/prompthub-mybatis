package com.prompthub.user.model.dao;

import com.prompthub.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
}
