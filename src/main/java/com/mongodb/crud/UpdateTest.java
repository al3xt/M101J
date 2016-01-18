package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class UpdateTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("updateTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            testCollection.insertOne(new Document("_id", i).append("x", i));
        }
        //testCollection.replaceOne(eq("x", 5), new Document("_id", 5).append("x", "20").append("updated", true));
        testCollection.updateOne(eq("x", 5), new Document("$set", new Document("x", 20)));
        testCollection.updateOne(eq("_id", 11), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(true));
        testCollection.updateOne(eq("_id", 12), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(false));

        testCollection.updateMany(gte("_id", 6), new Document("$inc", new Document("x", 1)));

        ArrayList<Document> documents = testCollection.find().into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }

    }
}
