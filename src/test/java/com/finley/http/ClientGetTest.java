package com.finley.http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Finley
 */
public class ClientGetTest {

    @Test
    public void testGet() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                httpResponse.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
