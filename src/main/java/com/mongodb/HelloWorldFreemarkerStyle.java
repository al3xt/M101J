package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 08.01.2016.
 */
public class HelloWorldFreemarkerStyle {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
        try {
            Template template = configuration.getTemplate("hello.ftl");
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("name", "Freemarker");
            template.process(dataMap, stringWriter);
            System.out.println(stringWriter);
        } catch (IOException|TemplateException e) {
            e.printStackTrace();
        }
    }
}
