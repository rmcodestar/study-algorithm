package com.study.algorithm.problem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.Description;

import java.util.OptionalInt;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rmcodestar on 2018. 4. 28..
 */
@Slf4j
public class MinimalChangeCountTest {

    @Description("greedy 알고리즘으로는 최적해를 구할 수 없다")
    @Test(expected = AssertionError.class)
    public void testcase1_greedy() {
        //Given
        int[] coins = {10, 50, 100, 120, 200};

        //Assert
        assertThat(calculateUsingGreedy(coins, 240), is(2));
    }

    private int calculateUsingGreedy(int[] coins, int change) {
        if (change == 0) {
            return 0;
        }

        OptionalInt max = IntStream.of(coins).filter(coin -> coin <= change).max();

        if (max.isPresent()) {
            log.info("selected coin : {}", max.getAsInt());
            return calculateUsingGreedy(coins, change - max.getAsInt()) + 1;
        }

        throw new IllegalStateException("can not calculate");
    }

    @Description("dynamic programming")
    @Test
    public void testcase2_dp() {
        //Given
        int[] coins = {10, 50, 100, 120, 200};

        //Assert
        assertThat(calculateUsingDP(coins, 240), is(2));
    }


    private int calculateUsingDP(int[] coins, int change) {
        int[] temps = new int[change + 1];

        for (int i = 1; i < temps.length; i++) {
            temps[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= change; j++) {
                temps[j] = Math.min(temps[j], temps[j - coins[i]] + 1);
            }

            log.info("result : {}", temps);
        }

        if (temps[change] > change) {
            throw new IllegalStateException("can not calculate, change : " + change);
        }

        return temps[change];
    }


    @Description("divide and conquer")
    @Test
    public void testcase3_divide_and_conquer() {
        //Given
        int[] coins = {10, 50, 100, 120, 200};

        //Assert
        assertThat(calculateRecursively(coins, 240), is(2));
    }

    private int calculateRecursively(int[] coins, int chagne) {
        int count = calculate(coins, chagne);

        if (count == Integer.MAX_VALUE) {
            throw new IllegalStateException("can not calculate, change : " + chagne);
        }

        return count;
    }

    private int calculate(int[] coins, int change) {
        if (change == 0) {
            return 0;

        } else if (change < 0) {
            return Integer.MAX_VALUE - 1;

        }

        int min = Integer.MAX_VALUE;
        for (int index = 0; index < coins.length; index++) {
            min = Math.min(min, calculate(coins, change - coins[index]) + 1);
        }


        return min;
    }


}
