package com.finley.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * @author Finley
 */
public class ClientConnectionTest {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URI baiduURI = new URIBuilder().setScheme("https").setHost("www.baidu.com").build();
        HttpGet httpGet = new HttpGet(baiduURI);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println(EntityUtils.toString(httpEntity));

        inputStream.close();
        response.close();
    }
}
