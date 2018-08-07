package com.method.userservice.service;

import com.method.userservice.entity.BaseEntity;
import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class GenericService<T extends BaseEntity> extends Dao<T> {

    public boolean insert(T t) {
        super.getDao().insertOne(new Document(t.toMap()));
        return true;
    }

    public boolean save(T t) {
        super.getDao().insertOne(new Document(t.toMap()));
        return true;
    }

    public void delete(String id) {

    }

    public boolean delete(T t) {
        return false;
    }

    public boolean delete(Criteria criteria) {
        return false;
    }

    public boolean update(Update update) {
        return false;
    }

    public boolean update(T older, T newer) {
        return false;
    }

    public T findOne(String id) {
        return null;
    }

    public T findOne(Criteria criteria) {
        return null;
    }

    public T findOne(Query query) {
        return null;
    }

    public List<T> find(Query query) {
        return null;
    }

    public List<T> find(List<String> ids) {
        return null;
    }

    public List<T> find(Criteria criteria) {
        return null;
    }

    public boolean exist(Criteria criteria) {
        return false;
    }

    public T aggregate(Aggregation aggregation) {
        return null;
    }
}
