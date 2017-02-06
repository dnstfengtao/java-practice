package com.finley.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.finley.logger.Log;
import com.finley.logger.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Finley
 */
public class SimpleGson {

    private final Log  log  = LoggerFactory.getLog(SimpleGson.class);
    private final Gson gson = new Gson();


    private void simpleToJson() {

        String simpleJSON = "";
        simpleJSON = gson.toJson("123");
        log.info(simpleJSON);

        simpleJSON = gson.toJson("simple");
        log.info(simpleJSON);

        simpleJSON = gson.toJson(1L);
        log.info(simpleJSON);

        int[] ints = {1};
        simpleJSON = gson.toJson(ints);
        log.info(simpleJSON);
    }


    private void deserialization() {
        int one = gson.fromJson("1", int.class);
        Integer one1 = gson.fromJson("1", Integer.class);
        log.info(one);
        log.info(one1);
    }


    private void deserializationCollection() {
        final Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();
        Collection<Integer> integers = gson.fromJson("[1,2,3,4]", collectionType);
        log.info(integers);
    }


    private void deserializationCollection1() {
        Collection<Object> collection = new ArrayList();
        collection.add("hello");
        collection.add(5);
        collection.add(new Event("GREETINGS", "guest"));
        log.info(gson.toJson(collection));
    }


    public static void main(String[] args) {
        SimpleGson simpleGson = new SimpleGson();
        simpleGson.simpleToJson();
        simpleGson.deserialization();
        simpleGson.deserializationCollection();
        simpleGson.deserializationCollection1();
    }
}
