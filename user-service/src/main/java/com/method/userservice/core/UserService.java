package com.method.userservice.core;

import com.method.userservice.entity.User;
import com.mongodb.client.model.InsertOneOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<User> {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean insert(User user) throws Exception {
        getDao().insertOne(new Document(user.toMap()));
//        mongoTemplate.save(user);
        return true;
    }

    @Override
    void delete(String id) {

    }

    @Override
    boolean delete(User user) {
        return false;
    }

    @Override
    boolean delete(Criteria criteria) {
        return false;
    }

    @Override
    boolean update(Update update) {
        return false;
    }

    @Override
    boolean update(User older, User newer) {
        return false;
    }

    @Override
    User queryOne(String id) {
        return null;
    }

    @Override
    User queryOne(Criteria criteria) {
        return null;
    }

    @Override
    List<User> query(List<String> ids) {
        return null;
    }

    @Override
    List<User> query(Criteria criteria) {
        return null;
    }

    @Override
    boolean exist(Criteria criteria) {
        return false;
    }

    @Override
    User aggregate(Aggregation aggregation) {
        return null;
    }

}
