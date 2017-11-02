package com.jpract.utils;

import java.net.InetAddress;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fengjiantao.
 * @since 2017-04-25.
 */
@Slf4j
public abstract class AbstractKeyGenerator {
    /**
     * 序列最小值.
     */
    private static final int SEQ_MIN = 1;
    /**
     * 序列最大值.
     */
    private static final int SEQ_MAX = 9999;
    /**
     * 序列截取位数.
     */
    private static final int SEQ_DIGITS = 4;
    /**
     * 序列变量.
     */
    private static final AtomicInteger SEQ_ATOMIC_INTEGER = new AtomicInteger(SEQ_MIN);
    /**
     * IP 分隔符.
     */
    private static final char IP_SPACER = '.';
    /**
     * IP后两位.
     */
    private static final String IP_AFTER_TWO;
    /**
     * IP截取位数.
     */
    private static final int IP_DIGITS = 2;
    /**
     * 获取IP异常随机数模.
     */
    private static final int IP_MOD = 99;
    /**
     * 订单号最大长度.
     */
    private static final int ORDER_NO_MAX_SIZE = 32;

    static {
        String ip;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();

            ip = ip.substring(ip.lastIndexOf(IP_SPACER) + 1);
            // 获取IP地址后两位
            ip = formatNumber(Long.valueOf(ip), IP_DIGITS);
        } catch (Exception e) {
            // 如果报错采用随机生成两位数
            log.error("can not get ip address", e);

            Random random = new Random(UUID.randomUUID().toString().hashCode());
            int randomNum = random.nextInt(IP_MOD);

            ip = formatNumber(randomNum, IP_DIGITS);
        }
        IP_AFTER_TWO = ip;
    }

    /**
     * 订单号生成器.
     *
     * @param businessCode 3位的业务码
     * @return orderNo orderNo = 时间（17位） + IP(2位) + SEQ（3位） + 业务码（3位） + 自定义（默认为空， 不允许超过4位）
     */
    public String generatorOrderKey(String businessCode) {
        return generatorOrderKey(businessCode, customOrderKey());
    }

    /**
     * 订单号生成器.
     *
     * @param businessCode 3位的业务码
     * @return orderNo orderNo = 时间（23位） + IP(2位) + SEQ（4位） + 业务码（3位） + 自定义（默认为空， 不允许超过4位）
     */
    private String generatorOrderKey(String businessCode, String customKey) {
        StringBuilder builder = new StringBuilder();
        String date = DateUtils.formatDate(new Date(), DateUtils.DATE_ALL_SIX);
        builder.append(date).append(IP_AFTER_TWO).append(formatNumber(getSeq(), SEQ_DIGITS)).append(businessCode).append(customKey);
        if (builder.length() > ORDER_NO_MAX_SIZE) {
            throw new IllegalStateException(String.format("OrderNo is to long, orderNo %s", builder.toString()));
        }
        return builder.toString();
    }

    /**
     * 自定义订单号后缀
     *
     * @return 自定以订单 key.
     */
    public abstract String customOrderKey();

    /**
     * 获取序列.
     */
    private static int getSeq() {
        int result = SEQ_ATOMIC_INTEGER.incrementAndGet();
        if (result <= SEQ_MAX) {
            return result;
        } else {
            SEQ_ATOMIC_INTEGER.set(SEQ_MIN);
            return SEQ_MIN;
        }
    }

    /**
     * 格式化 number.
     *
     * @param number 参数.
     * @param digits 最小位数.
     * @return 格式化后结果.
     */
    private static String formatNumber(long number, int digits) {
        final NumberFormat nf = NumberFormat.getInstance();

        nf.setMaximumIntegerDigits(digits);
        nf.setMinimumIntegerDigits(digits);
        nf.setGroupingUsed(false); // 去除分组
        return nf.format(number);
    }

}
