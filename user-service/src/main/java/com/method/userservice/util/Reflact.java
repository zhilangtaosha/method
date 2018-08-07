package com.method.userservice.util;

import com.method.userservice.entity.User;
import org.springframework.data.mongodb.core.mapping.Document;

public class Reflact {
    public static String getCollectionName(Class clazz) {
        if (clazz.isAnnotationPresent(Document.class)) {
            Document annotation = (Document) clazz.getAnnotation(Document.class);
            System.out.println(annotation.collection());
            return annotation.collection();
        } else
            return clazz.getSimpleName();
    }

    public static void main(String[] args) {
        getCollectionName(User.class);
    }
}
