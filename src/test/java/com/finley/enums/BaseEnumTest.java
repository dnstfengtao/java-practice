package com.finley.enums;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 1/4/17.
 */
public class BaseEnumTest {

    enum BaseEnum {
        ONE, TWO, THREE
    }

    @Test
    public void testBaseEnum() {
        EnumMap<BaseEnum, String> baseEnumStringEnumMap = new EnumMap<>(BaseEnum.class);
        baseEnumStringEnumMap.put(BaseEnum.ONE, "one");
        baseEnumStringEnumMap.put(BaseEnum.TWO, "two");
        baseEnumStringEnumMap.put(BaseEnum.THREE, "three");

        System.out.println(baseEnumStringEnumMap);
    }

    @Test
    public void testValueOf() {
        final BaseEnum baseEnum = BaseEnum.valueOf("ONE");
        Assert.assertEquals(BaseEnum.ONE, baseEnum);
    }
}
