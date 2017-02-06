package com.finley.http;

import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;

/**
 * @author Finley
 */
public class HttpResponseTest {
    public static void main(String[] args) {
        HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        System.out.println(httpResponse.getProtocolVersion());
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        System.out.println(httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(httpResponse.getStatusLine().toString());
        httpResponse.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
        httpResponse.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = httpResponse.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = httpResponse.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        System.out.println(Arrays.toString(headers));
    }
}
