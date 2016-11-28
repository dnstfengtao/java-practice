package com.finley.http;

import java.net.URI;
import java.net.URL;

/**
 * @author Finley
 */
public class URITest {
    public static void main(String[] args) throws Exception {
        URI uri = new URI("www.baidu.com");
        System.out.println(uri.getScheme());
        System.out.println("uri host " + uri.getHost());
        System.out.println("uri path " + uri.getPath());
        URL url = new URL("http://www.baidu.com");
        System.out.println("url host " + url.getHost());
        System.out.println("url path " + url.getPath());
    }
}
