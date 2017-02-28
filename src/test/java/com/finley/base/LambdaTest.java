package com.finley.base;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author fengjiantao.
 * @since 2/28/17.
 */
public class LambdaTest {

    @Test
    public void baseTest() {
        final Runnable runnable = () -> System.out.println("Hello world");
        runnable.run();
        new Thread(runnable).start();
    }

    @Test
    public void sortTest() {
        final String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};

        final String[] result = new String[players.length];

        System.arraycopy(players, 0, result, 0, players.length);

        Arrays.sort(result, String::compareTo);

        System.out.println(Arrays.toString(players));
        System.out.println(Arrays.toString(result));
    }

}
