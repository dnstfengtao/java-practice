package com.jpract.utils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;


/**
 * @author fengjiantao.
 * @since 4/26/17.
 */
public final class BatchDivideUtils {

    /**
     * 使用指定方法处理分组. 参数集合不能为空, 分批尺寸需要大于 0, 消费方法不能为空.
     * 将按照 batchSize 将参数集合分组, 并由传入的 subSetConsumer 子集合处理器消费.
     *
     * @param lists          源集合.
     * @param batchSize      批量处理大小.
     * @param subSetConsumer 消费方法.
     * @param <T>            类型.
     */
    public static <T> void divideByOperation(List<T> lists, int batchSize, Consumer<List<T>> subSetConsumer) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(lists), "The input list should not empty.");
        Preconditions.checkArgument(batchSize > 0, "The input batch size should larger than zero.");
        Objects.requireNonNull(subSetConsumer);
        divideByBatchLength(lists.size(), batchSize).forEach(pair -> subSetConsumer.accept(lists.subList(pair.getLeft(), pair.getRight())));
    }

    /**
     * 将长度按照 batchSize 分组.
     *
     * @param length    长度.
     * @param batchSize 分组长度.
     * @return list of pair, 分组后的 pair 集合, 每个元素代表一组子集合下标.
     */
    private static List<Pair<Integer, Integer>> divideByBatchLength(int length, int batchSize) {
        if (length == 0 || batchSize == 0) {
            return Collections.emptyList();
        }
        Preconditions.checkArgument(length > 0, "The length should larger than zero.");
        Preconditions.checkArgument(batchSize > 0, "The batch size should larger than zero.");
        List<Pair<Integer, Integer>> result = Lists.newArrayList();

        int howManyBatch = length / batchSize;

        if (howManyBatch == 0) {
            result.add(Pair.of(0, length));
            return result;
        }

        for (int i = 0; i < howManyBatch; i++) {
            result.add(Pair.of(i * batchSize, (i * batchSize) + batchSize));
        }

        if (length % batchSize != 0) {
            result.add(Pair.of(howManyBatch * batchSize, length));
        }

        return result;
    }
}
