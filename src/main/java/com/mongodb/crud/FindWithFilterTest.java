package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class FindWithFilterTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("findTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            testCollection.insertOne(new Document().
                    append("x", new Random().nextInt(2)).
                    append("y", new Random().nextInt(100)));
        }

        //Bson filter = new Document("x", 0).append("y", new Document("$gt", 10).append("$lt", 90));
        Bson filter = and(eq("x", 10), gt("y", 10), lt("y", 90));
        ArrayList<Document> documents = testCollection.find(filter).into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }

    }
}
