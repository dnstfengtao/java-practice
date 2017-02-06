package com.finley.http;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

/**
 * @author Finley
 */
public class URIBuilderTest {
    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http")
                .setHost("www.baidu.com")
                .setPath("/search")
                .setParameter("q", "httpclinet")
                .build();
        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());
    }
}
