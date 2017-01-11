package com.finley.net;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Throwables;

/**
 * @author fengjiantao.
 * @since 1/11/17.
 */
public class URLTest {

    @Test
    public void testConstruct() throws Exception {
        URL url = null;
        try {
            url = new URL("https", "www.baidu.com", "/testFile?queryContent#testFragment");
        } catch (MalformedURLException e) {
            Throwables.propagate(e);
        }
        Assert.assertEquals("www.baidu.com", url.getHost());
        Assert.assertEquals("/testFile?queryContent", url.getFile());
        Assert.assertEquals("https", url.toURI().getScheme());
        Assert.assertEquals("testFragment", url.toURI().getFragment());
    }
}
