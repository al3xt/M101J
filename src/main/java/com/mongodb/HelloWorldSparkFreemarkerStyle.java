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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 08.01.2016.
 */
public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                StringWriter stringWriter = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("name", "Freemarker");
                    template.process(dataMap, stringWriter);
                } catch (IOException | TemplateException e) {
                    halt(500);
                    e.printStackTrace();
                }
                return stringWriter;
            }
        });

    }
}
