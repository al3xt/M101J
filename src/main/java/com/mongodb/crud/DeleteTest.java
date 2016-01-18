package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class DeleteTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("updateTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            testCollection.insertOne(new Document("_id", i).append("x", i));
        }

        testCollection.deleteMany(gt("_id", 5));
        testCollection.deleteOne(eq("_id", 2));
        ArrayList<Document> documents = testCollection.find().into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }

    }
}
