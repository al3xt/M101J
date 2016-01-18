package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class FindTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("findTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            testCollection.insertOne(new Document("x", i));
        }

        System.out.println("Find one:");
        Document first = testCollection.find().first();
        printJson(first);
        System.out.println("Find all with into:");
        ArrayList<Document> documents = testCollection.find().into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }
        System.out.println("Find all with cursor:");
        MongoCursor<Document> cursor = testCollection.find().iterator();
        try {
            while (cursor.hasNext())
            {
                Document next = cursor.next();
                printJson(next);
            }
        } finally {
            cursor.close();
        }
        System.out.println("Count:" + testCollection.count());
    }

}
