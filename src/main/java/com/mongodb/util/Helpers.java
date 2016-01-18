package com.mongodb.util;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;

/**
 * Created by Alexey on 17.01.2016.
 */
public class Helpers {

    public static void printJson(Document document) {
        JsonWriter writer = new JsonWriter(new StringWriter(), new JsonWriterSettings(JsonMode.SHELL, true));
        new DocumentCodec().encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(writer.getWriter());
        System.out.flush();
    }
}
