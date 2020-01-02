package com.kgc.service.impl;

import com.kgc.dao.RoleMapper;
import com.kgc.dao.UserMapper;
import com.kgc.pojo.Role;
import com.kgc.pojo.User;
import com.kgc.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }

    @Override
    public User selectByUser(String userCode, String password) {
        User u = new User();
        u.setUsercode(userCode);
        User user = userMapper.selectOne(u);
        if(user != null && user.getUserpassword().equals(password)){
            return user;
        }
        return null;
    }
}
