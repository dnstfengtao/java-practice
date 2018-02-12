package com.finley.proxy;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/12/18.
 */
public class StreamTest {

    @Test
    public void testStream() {
        Optional<BigDecimal> result = Stream.of(new BigDecimal(10), new BigDecimal(11), new BigDecimal(12))
                .filter(item -> item.compareTo(BigDecimal.valueOf(12)) != 0)
                .reduce((sum, item) -> {
                    sum = sum.add(item);
                    return sum;
                });

        System.out.println(result.orElse(BigDecimal.ZERO));
    }

}
