package com.method.userservice.core;

import com.method.userservice.entity.User;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ValidationAction;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.operation.CreateCollectionOperation;
import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.logging.Level;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static java.util.logging.Logger.getLogger;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoTransaction {

    private static MongoClient mongoClient;
    private static MongoCollection<User> userMongoCollection;

    private static void initMongoDB(String mongodbURI) {
        getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register("com.method.entity").build()));

        MongoClientOptions.Builder options = new MongoClientOptions.Builder().codecRegistry(codecRegistry);
        MongoClientURI uri = new MongoClientURI(mongodbURI, options);
        mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("test");
//        db.drop();
//        db.createCollection("user", productJsonSchemaValidator());
        userMongoCollection = db.getCollection("user", User.class);
    }

//    private static CreateCollectionOperations productJsonSchemaValidator() {
//        return new CreateCollectionOperations().validationOptions(new ValidationOptions().validationAction(ValidationAction.ERROR)
//                .validationAction(ValidationAction.ERROR).validator(BsonDocument.parse("")));
//    }

    public static void transfer(ClientSession session, String from, Double amount) {
        try {
            userMongoCollection.updateOne(
                    session,
                    eq("_id", from),
                    inc("balance", amount)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void receive(ClientSession session, String to, Double amount) {
        try {
            userMongoCollection.updateOne(
                    session,
                    eq("_id", to),
                    inc("balance", amount)
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void transfer(String from, String to, Double amount) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            transfer(clientSession, from, amount);
            receive(clientSession, to, amount);
            clientSession.commitTransaction();

        } catch (Exception e) {
            e.printStackTrace();
            clientSession.abortTransaction();

        } finally {
            clientSession.close();
            printDatabaseState();
        }
    }

    private static void printDatabaseState() {
    }


}
