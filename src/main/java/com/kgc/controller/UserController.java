package com.kgc.controller;

import com.kgc.dao.RoleMapper;
import com.kgc.pojo.Role;
import com.kgc.pojo.User;
import com.kgc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleMapper roleMapper;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, String password, HttpSession session){
        User user = userService.selectByUser(userCode, password);
        if(user == null){
            session.setAttribute("error","用户户名或密码错误");
            return "redirect:/user/login";
        }
        Role role = new Role();
        role.setId(user.getUserrole());
        Role role1 = roleMapper.selectOne(role);
        session.setAttribute("role",role1);
        session.setAttribute("user",user);
        return "frame";
    }

}
