package com.prompthub.user.service;

import com.prompthub.user.model.dao.UserMapper;
import com.prompthub.user.model.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserDTO login(String id, String password) {
//        System.out.println("id = " + id);
        UserDTO user = userMapper.selectUserById(id);
//        System.out.println("조회 결과 = " + user);
        

        if(user == null) {
            throw new RuntimeException("로그인 실패");
        }

        if(!user.getPasswordHash().equals(password)){
            throw new RuntimeException("로그인 실패");
        }

        return user;
    }
}
