package com.jpract.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jiantao.feng
 * @since 15/10/2016
 */
public class KeyGenerator extends AbstractKeyGenerator {
    private static final KeyGenerator SINGLE = new KeyGenerator();

    private KeyGenerator() {
    }

    public static String generateKey() {
        return SINGLE.generatorOrderKey(StringUtils.EMPTY);
    }

    /**
     * 自定义订单号后缀，默认为空.
     */
    @Override
    public String customOrderKey() {
        return StringUtils.EMPTY;
    }
}
