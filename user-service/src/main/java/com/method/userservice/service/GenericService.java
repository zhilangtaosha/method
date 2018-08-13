package com.method.userservice.service;

import com.google.gson.Gson;
import com.method.userservice.entity.BaseEntity;
import com.method.userservice.entity.User;
import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import org.bson.Document;
import org.bson.codecs.Decoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class GenericService<T extends BaseEntity> extends Dao<T> {

    @Autowired
    MongoClient mongoClient;

    public boolean insert(T t) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            super.getDao().insertOne(clientSession, Document.parse(new Gson().toJson(t)));
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean save(T t) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            super.getDao().insertOne(clientSession, Document.parse(new Gson().toJson(t)));
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean delete(String id) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            super.getDao().deleteOne(clientSession, new Document("id", id));
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean delete(T t) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            super.getDao().deleteOne(clientSession, Document.parse(new Gson().toJson(t)));
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean delete(Criteria criteria) {


        ClientSession clientSession = mongoClient.startSession();
        try {
            super.getDao().deleteOne(clientSession, criteria.getCriteriaObject());
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean update(Update update) {

        ClientSession clientSession = mongoClient.startSession();
        try {
            super.getDao().deleteOne(clientSession, update.getUpdateObject());
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

    public boolean update(T older, T newer) {
        return false;
    }

    public T findOne(String id) {

        T t = (T) super.getDao().find(Criteria.where("name").is(id).getCriteriaObject()).first();
        return t;
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


    public boolean transfer(String from, String to, Double money) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            super.getDao().updateOne(clientSession, Criteria.where("name").is(to).getCriteriaObject(), inc("balance", money));
            int a = 1 / 0;
            super.getDao().updateOne(clientSession, Criteria.where("name").is(from).getCriteriaObject(), inc("balance", -money));

            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();
            return false;
        } finally {
            clientSession.close();
        }
    }

}
