package com.method.userservice.core;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public abstract class GenericService<T> extends Dao<T> {
    abstract boolean insert(T t) throws Exception;

    abstract void delete(String id);

    abstract boolean delete(T t);

    abstract boolean delete(Criteria criteria);

    abstract boolean update(Update update);

    abstract boolean update(T older, T newer);

    abstract T queryOne(String id);

    abstract T queryOne(Criteria criteria);

    abstract List<T> query(List<String> ids);

    abstract List<T> query(Criteria criteria);

    abstract boolean exist(Criteria criteria);

    abstract T aggregate(Aggregation aggregation);
}
