package com.method.userservice.service;

import com.method.userservice.entity.BaseEntity;
import com.method.userservice.util.Reflact;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public class Dao<T extends BaseEntity> {

    @Autowired
    private MongoDatabase mongoDatabase;

    private MongoCollection mongoTemplate;

    private Class<T> type;

    public Dao() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public MongoCollection getDao() {
        if (mongoTemplate == null)
            mongoTemplate = mongoDatabase.getCollection(Reflact.getCollectionName(type));
        return mongoTemplate;
    }

}
