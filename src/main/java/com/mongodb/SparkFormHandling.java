package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 08.01.2016.
 */
public class SparkFormHandling {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template template = configuration.getTemplate("fruitPicker.ftl");
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("fruits", Arrays.asList("apple", "orange", "pear", "banana"));
                    template.process(dataMap, stringWriter);
                } catch (IOException | TemplateException e) {
                    halt(500);
                    e.printStackTrace();
                }
                return stringWriter;
            }
        });

        Spark.post(new Route("favourite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why don't you pick one?";
                } else {
                    return "Your favourite fruit is " + fruit;
                }

            }
        });
    }
}
