package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class FindWithProjectionTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("findProjectionTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            testCollection.insertOne(new Document().
                    append("x", new Random().nextInt(2)).
                    append("y", new Random().nextInt(100)));
        }

        //Bson filter = new Document("x", 0).append("y", new Document("$gt", 10).append("$lt", 90));
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));
        //Bson projection = new Document("x", 0);
        //Bson projection = Projections.exclude("x", "_id");
        Bson projection = fields(include("y"), excludeId());
        List<Document> documents = testCollection.find(filter).projection(projection).into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }

    }

}
