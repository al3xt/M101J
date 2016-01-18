package com.mongodb.hw2_3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Sorts.orderBy;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by Alexey on 18.01.2016.
 */
public class RemoveWorstHomeworkService {

    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("students");
        MongoCollection<Document> grades = database.getCollection("grades");
        Bson filter = eq("type", "homework");
        Bson sort = orderBy(ascending("student_id"), ascending("score"));
        List<Document> homeWorks = grades.find(filter).sort(sort).into(new ArrayList<Document>());
        Integer lastStudentId = null;
        for(Document homework : homeWorks){
            Integer studentId = homework.getInteger("student_id");
            if (lastStudentId == null || !lastStudentId.equals(studentId)){
                System.out.println("To remove:");
                printJson(homework);
                grades.deleteOne(new Document("_id", homework.getObjectId("_id")));
            } else {
                System.out.println("To leave:");
                printJson(homework);
            }
            lastStudentId = studentId;
        }
        System.out.println("Grades Count:" + grades.count());
        System.out.println("Homeworks Count:" + homeWorks.size());
    }
}
