package com.method.userservice;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCriteriaToMap() {
        System.out.println(new Gson().toJson(Criteria.where("id").is("123")));
    }

}
