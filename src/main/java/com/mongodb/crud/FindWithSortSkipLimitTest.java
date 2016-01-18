package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class FindWithSortSkipLimitTest {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> testCollection = database.getCollection("findProjectionTest");

        testCollection.drop();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
                testCollection.insertOne(new Document().append("i", i).append("j", j));
            }
        }

        //Bson filter = new Document("x", 0).append("y", new Document("$gt", 10).append("$lt", 90));
        //Bson projection = new Document("x", 0);
        //Bson projection = Projections.exclude("x", "_id");
        Bson projection = excludeId();
        //Bson sort = new Document("i", 1).append("j", -1);
        Bson sort = orderBy(ascending("i"), descending("j"));
        List<Document> documents = testCollection.find().projection(projection).sort(sort).skip(20).limit(50).into(new ArrayList<Document>());
        for(Document document : documents){
            printJson(document);
        }

    }

}
