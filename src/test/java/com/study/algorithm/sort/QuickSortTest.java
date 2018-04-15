package com.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Created by rmcodestar on 2018. 4. 15..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class QuickSortTest {
    private QuickSort quickSort = new QuickSort();

    @Test
    public void test() {
        // testcase(IntStream.of(9, 20, 6, 10, 14, 8, 60, 11), IntStream.of(6, 8, 9, 10, 11, 14, 20, 60));
        testcase(IntStream.of(6, 2, 9, 3, 4, 5, 7, 1, 8), IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void test2() {
        testcase(IntStream.of(1, 2, 1), IntStream.of(1, 1, 2));
    }

    @Test
    public void test3() {
        testcase(IntStream.of(1), IntStream.of(1));
    }

    private void testcase(IntStream originalStream, IntStream expectedSteam) {
        //Given
        List<Integer> originals = originalStream.boxed().collect(Collectors.toList());
        List<Integer> expected = expectedSteam.boxed().collect(Collectors.toList());

        //When
        quickSort.sort(originals);

        //Assert
        log.info("result : {}", originals);
        assertTrue(originals.equals(expected));
    }
}