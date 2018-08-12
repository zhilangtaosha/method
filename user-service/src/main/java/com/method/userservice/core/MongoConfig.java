package com.method.userservice.core;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.util.logging.Level;

import static java.util.logging.Logger.getLogger;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Bean
    public MongoClient mongoClient() {

        getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().register("com.method.userservice.entity").build()));

        MongoClientOptions.Builder options = new MongoClientOptions.Builder().codecRegistry(codecRegistry);
        MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017", options);


        return new MongoClient(uri);
    }


    @Deprecated
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "test");
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase("test");
    }

    @Override
    protected String getDatabaseName() {
        return "test";
    }

}
