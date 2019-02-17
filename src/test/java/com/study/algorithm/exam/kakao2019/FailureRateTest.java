package com.study.algorithm.exam.kakao2019;

import org.junit.Test;

/**
 * 1022 (+11)
 */
public class FailureRateTest {
    @Test
    public void test1() {
        FailureRate failureRate = new FailureRate();

        int N = 5;
        int[] inputs = {2, 1, 2, 6, 2, 4, 3, 3};

        for (Integer integer : failureRate.solution(N, inputs)) {
            System.out.println(integer);
        }
    }

    @Test
    public void test2() {
        FailureRate failureRate = new FailureRate();

        int N = 4;
        int[] inputs = {4, 4, 4, 4, 4};

        for (Integer integer : failureRate.solution(N, inputs)) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3() {
        FailureRate failureRate = new FailureRate();

        int N = 3;
        int[] inputs = {2, 2, 2};

        for (Integer integer : failureRate.solution(N, inputs)) {
            System.out.println(integer);
        }
    }

}