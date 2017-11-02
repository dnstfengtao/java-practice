package com.jpract.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jiantao.feng
 * @since 26/10/2016
 */
public class MD5Helper {
    /**
     * 签名字符串.
     *
     * @param text    需要签名的字符串.
     * @param charset 编码格式.
     * @return 签名结果
     */
    public static String sign(String text, String charset) {
        return DigestUtils.md5Hex(getContentBytes(text, charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (StringUtils.isBlank(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("When sign the content error occur the char set is: " + charset);
        }
    }
}
