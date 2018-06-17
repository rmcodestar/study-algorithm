package com.study.algorithm.problem;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by rmcodestar on 2018. 6. 17..
 */
public class PrimeInRangeProblemTest {
    PrimeInRangeProblem primeInRangeProblem = new PrimeInRangeProblem();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test(timeout = 2000)
    public void testcase1() {
        primeInRangeProblem.printPrime(1, 10);
        assertThat(outputCapture.toString(), is("2\n" +
                "3\n" +
                "5\n" +
                "7\n"));
    }

    @Test(timeout = 2000)
    public void testcase2() {
        primeInRangeProblem.printPrime(104549, 104659);
        assertThat(outputCapture.toString(), is("104549\n" +
                "104551\n" +
                "104561\n" +
                "104579\n" +
                "104593\n" +
                "104597\n" +
                "104623\n" +
                "104639\n" +
                "104651\n" +
                "104659\n"));
    }

    @Test(timeout = 2000)
    public void testcase3() {
        primeInRangeProblem.printPrime(1, 1);
        assertThat(outputCapture.toString(), is(""));
    }
}