package com.kgc.test;

import com.kgc.pojo.User;
import com.kgc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {
    @Resource
    private UserService userService;
    @Test
    public void test01(){
        List<User> userList = userService.selectAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
