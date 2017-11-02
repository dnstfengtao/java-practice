package com.jpract.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

/**
 * 重复请求监听器.
 * v1, 不添加超时机制 -- 调用业务必须保证单次调用被 release.
 *
 * @author fengjiantao.
 * @since 10/17/17.
 */
@Component
public class RepeatRequestMonitor {

    private static final Map<String, String> requestMonitorMap = Maps.newConcurrentMap();

    /**
     * 尝试访问, 用于在访问接口前加锁, 成功返回 true, 失败返回 false.
     *
     * @param requestKey 访问 key.
     * @return true.
     */
    public boolean tryRequest(String requestKey) {
        // 最短路径.
        if (requestMonitorMap.containsKey(requestKey)) {
            return Boolean.FALSE;
        }
        // 读写操作-加同步.
        synchronized (requestMonitorMap) {
            // 二次判断.
            if (requestMonitorMap.containsKey(requestKey)) {
                return Boolean.FALSE;
            }
            requestMonitorMap.put(requestKey, requestKey);
            return Boolean.TRUE;
        }
    }

    /**
     * 释放对接口访问.
     *
     * @param requestKey 访问 key.
     */
    public void release(String requestKey) {
        requestMonitorMap.remove(requestKey);
    }

}
