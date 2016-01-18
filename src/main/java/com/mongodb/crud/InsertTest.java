package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 17.01.2016.
 */
public class InsertTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> users = database.getCollection("users");
        Document userSmith = new Document().append("name", "Smith").append("age", 30).append("profession", "programmer");
        printJson(userSmith);
        users.insertOne(userSmith);
        printJson(userSmith);
    }
}
