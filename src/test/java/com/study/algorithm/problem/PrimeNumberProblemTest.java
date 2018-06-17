package com.study.algorithm.problem;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Created by rmcodestar on 2018. 6. 17..
 */
public class PrimeNumberProblemTest {
    PrimeNumberProblem primeNumber = new PrimeNumberProblem();
    int[] randomInput = new int[100];

    @Before
    public void setup() {
        randomInput = new int[100];

        for (int index = 0; index < 100; index++) {
            randomInput[index] = (int) (Math.random() * 1000);
        }
    }

    @Test(timeout = 2)
    public void testRandom() {
        primeNumber.findPrimeCount(randomInput);
    }

    @Test(timeout = 2)
    public void testcase1() {
        assertThat(primeNumber.findPrimeCount(new int[]{1, 2, 3, 5, 7, 8, 9, 10, 11}), is(5));
    }
}