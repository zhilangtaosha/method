package com.method.userservice.core;

import com.method.userservice.util.Reflact;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

public class Dao<T> {

    @Autowired
    MongoDatabase mongoDatabase;

    private Class<T> type;

    public Dao() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public MongoCollection getDao() throws Exception {
        return mongoDatabase.getCollection(Reflact.getCollectionName(type));
    }

}
