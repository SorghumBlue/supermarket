package com.kgc.service;

import com.kgc.pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();
    User selectByUser(String userCode, String password);
}
