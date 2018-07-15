package com.study.algorithm.problem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

/**
 * Created by rmcodestar on 2018. 7. 15..
 */
@Slf4j
public class MatrixExponentiationProblemTest {

    @Test(timeout = 1000)
    public void testcase1() {

        StopWatch stopWatch = new StopWatch();
        MatrixExponentiationProblem problem = new MatrixExponentiationProblem();

        Long[][] input = new Long[5][5];

        for (int y = 0; y < 5; y++)
            for (int x = 0; x < 5; x++)
                input[y][x] = 1000L;

        stopWatch.start();

        problem.calculateMatrix(input, 1000_000_000_000L, 5);

        stopWatch.stop();

        System.out.print(stopWatch.prettyPrint());
    }

}
