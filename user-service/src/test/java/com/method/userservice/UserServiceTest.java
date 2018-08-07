package com.method.userservice;

import com.method.userservice.core.UserService;
import com.method.userservice.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void insertTest() throws Exception {
        User user = new User("1", "冯才文", "123456", "asdf@qq.com");
        UserService userService1 = new UserService();

        userService1.insert(user);
    }
}
