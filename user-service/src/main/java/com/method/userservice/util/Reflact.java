package com.method.userservice.util;

import com.method.userservice.entity.User;
import org.springframework.data.mongodb.core.mapping.Document;

public class Reflact {
    public static String getCollectionName(Class clazz) {
        if (clazz.isAnnotationPresent(Document.class)) {
            Document annotation = (Document) clazz.getAnnotation(Document.class);
            if (annotation.collection() != null || "".equals(annotation.collection()))
                return annotation.collection();
            else
                return clazz.getSimpleName().toLowerCase();
        } else
            return clazz.getSimpleName().toLowerCase();
    }

    public static void main(String[] args) {

        System.out.println(getCollectionName(User.class));

    }
}
