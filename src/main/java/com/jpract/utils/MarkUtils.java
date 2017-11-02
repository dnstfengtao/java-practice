package com.jpract.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jiantao.feng
 * @since 2016年3月24日
 */
public class MarkUtils {

    private static final String DEFAULT_MARK_SYMBOL = "*";

    /**
     * 将指定位置的字符串替换为星号.
     * 并合并替换后的星号.
     * 替换方式为左闭右开， 包含起始位置不包含结束位置.
     *
     * @param original   原字符串.
     * @param startIndex 起始位置.
     * @param endIndex   结束位置.
     * @return 处理后文本.
     */
    public static String markAndMerge(String original, int startIndex, int endIndex) {
        String result = mark(original, startIndex, endIndex);
        if (StringUtils.isNotBlank(result)) {
            result = result.replaceAll("\\*+", "\\*");
        }
        return result;
    }

    /**
     * 将指定位置的字符串替换为指定符号.
     * 替换方式为左闭右开， 包含起始位置不包含结束位置.
     *
     * @param original   原字符串.
     * @param startIndex 起始位置.
     * @param endIndex   结束位置.
     * @return 处理后文本.
     */
    public static String mark(String original, int startIndex, int endIndex, String markSymbol) {
        if (StringUtils.isBlank(original)) {
            return StringUtils.EMPTY;
        }
        if (endIndex < 0) {
            endIndex = original.length() + endIndex;
        }
        if (endIndex < 0 || startIndex < 0) {
            return original;
        }
        if (endIndex < startIndex) {
            return original;
        }
        if (original.length() < startIndex + 1) {
            return original;
        }
        return markAsSymbol(original, startIndex, endIndex, markSymbol);
    }

    /**
     * 将指定位置的字符串替换为 '*'.
     * 替换方式为左闭右开， 包含起始位置不包含结束位置.
     *
     * @param original   原字符串.
     * @param startIndex 起始位置.
     * @param endIndex   结束位置.
     * @return 处理后文本.
     */
    public static String mark(String original, int startIndex, int endIndex) {
        return mark(original, startIndex, endIndex, DEFAULT_MARK_SYMBOL);
    }

    /**
     * 将指定位置的字符串替换为符号.
     * 替换方式为左闭右开， 包含起始位置不包含结束位置.
     *
     * @param original   原字符串.
     * @param startIndex 起始位置.
     * @param endIndex   结束位置.
     * @param markSymbol 符号.
     * @return 处理后文本.
     */
    private static String markAsSymbol(String original, int startIndex, int endIndex, String markSymbol) {
        if (original.length() > endIndex) {
            String before = original.substring(0, startIndex);
            String end = original.substring(endIndex, original.length());
            return before + generateMarkSymbol(markSymbol, endIndex - startIndex) + end;
        } else {
            String before = original.substring(0, startIndex);
            return before + generateMarkSymbol(markSymbol, original.length() - startIndex);
        }
    }

    /**
     * 生成符号 Mark.
     *
     * @param markSymbol 符号.
     * @param num        符号数量.
     * @return 指定数量的符号.
     */
    private static String generateMarkSymbol(String markSymbol, int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(markSymbol);
        }
        return result.toString();
    }
}
