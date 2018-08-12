package com.method.userservice.service;

import com.method.userservice.entity.User;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends GenericService<User> {

    @Override
    public boolean insert(User user) {
        super.save(user);
        return true;
    }

    @Override
    public boolean save(User user) {
        super.save(user);
        return true;
    }

    @Override
    public boolean delete(String id) {
        return true;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean delete(Criteria criteria) {
        return false;
    }

    @Override
    public boolean update(Update update) {
        return false;
    }

    @Override
    public boolean update(User older, User newer) {
        return false;
    }

    @Override
    public User findOne(String id) {
        return null;
    }

    @Override
    public User findOne(Criteria criteria) {
        return null;
    }

    @Override
    public User findOne(Query query) {
        return null;
    }

    @Override
    public List<User> find(List<String> ids) {
        return null;
    }

    @Override
    public List<User> find(Query query) {
        return null;
    }

    @Override
    public List<User> find(Criteria criteria) {
        return null;
    }

    @Override
    public boolean exist(Criteria criteria) {
        return false;
    }

    @Override
    public User aggregate(Aggregation aggregation) {
        return null;
    }


}
