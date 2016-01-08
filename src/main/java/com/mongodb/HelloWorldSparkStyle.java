package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Alexey on 08.01.2016.
 */
public class HelloWorldSparkStyle {

    public static void main(String[] args) {
        Spark.get(new Route("/"){
            public Object handle(Request request, Response response) {
                return "Hello world from Spark!";
            }
        });
    }
}
