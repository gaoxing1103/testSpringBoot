package com.example.demo.mapper;

import com.example.demo.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser();

    List<User> selectUserByName(String userName);

    User getUserByUserName(String userName);

    int addUser(User user);

    int deleteUserById(int id);
}
