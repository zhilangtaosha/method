package com.method.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    private String name;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() + '\'' +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


//    public static Map<String, Object> toMap(User user) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            Class clazz = user.getClass();
//            Method[] methods = clazz.getMethods();
//            for (Method method : methods)
//                if (method.invoke(user) != null)
//                    map.put("", null);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }


    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        if (super.getId() != null)
            map.put("_id", super.getId());
        if (name != null)
            map.put("name", name);
        if (password != null)
            map.put("password", password);
        if (email != null)
            map.put("email", email);
        return map;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new User().test();

        System.out.println(User.class.getSimpleName());

//        System.out.println(User.toMap(new User("14", "12", "234")));
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
