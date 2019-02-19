package com.study.algorithm.exam.kakao2019;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 1033 (+11)
 */
public class MakeTripPathTest {

    @Test
    public void test() {
        MakeTripPath makeTripPath = new MakeTripPath();

        int[][] nodeInfos = {
                {5, 3}
                , {11, 5}
                , {13, 3}
                , {3, 5}
                , {6, 1}
                , {1, 3}
                , {8, 6}
                , {7, 2}
                , {2, 2}

        };

        System.out.println(makeTripPath.solution(nodeInfos));
    }
}