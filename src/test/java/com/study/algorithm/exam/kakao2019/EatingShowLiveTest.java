package com.study.algorithm.exam.kakao2019;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EatingShowLiveTest {
    private EatingShowLive sut = new EatingShowLive();

    @Test
    public void test1() {
        int[] times = {3, 1, 2};
        int k = 5;

        assertThat(sut.solution(times, k), is(1));
    }

    @Test(timeout = 1000)
    public void test2() {
        int[] times = new int[2_000];

        for (int index = 0; index < times.length; index++) {
            times[index] = 1000;
        }

        long k = 2000_000L - 1;

        assertThat(sut.solution(times, k), is(2000));
    }

    @Test
    public void test3() {
        int[] times = {1, 3, 1, 3, 0};

        long k = 5;

        assertThat(sut.solution(times, k), is(4));
    }

    @Test
    public void test4() {
        int[] times = {1000};

        long k = 999;

        assertThat(sut.solution(times, k), is(1));
    }
}