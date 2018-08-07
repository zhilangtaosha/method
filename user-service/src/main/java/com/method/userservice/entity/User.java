package com.method.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    String id;
    String name;
    String password;
    String email;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (!(id == null))
            map.put("_id", id);
        if (!(name == null))
            map.put("name", name);
        if (!(password == null))
            map.put("password", password);
        if (!(email == null))
            map.put("email", email);
        return map;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new User().test();

        System.out.println(User.class.getSimpleName());
    }

    public class A<T> {
        Class<T> type;

        public A()

        {

            type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }

    }

    public class B extends A<User> {
        public B() {
        }
    }

    public void test() {
        System.out.println(new B().type.getSimpleName());
    }


}
