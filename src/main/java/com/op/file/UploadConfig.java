package com.op.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class UploadConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    public Mongo mongo() throws Exception {
        return new MongoClient(host);
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception{
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }
}
