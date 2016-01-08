package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Alexey on 08.01.2016.
 */
public class SparkRoutes {

    public static void main(String[] args) {
        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                return "Hello world\n";
            }
        });
        Spark.get(new Route("/test") {
            public Object handle(Request request, Response response) {
                return "This is a test page\n";
            }
        });
        Spark.get(new Route("/echo/:thing") {
            public Object handle(Request request, Response response) {
                return request.params(":thing");
            }
        });
    }
}
