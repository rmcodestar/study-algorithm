package com.study.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Created by rmcodestar on 2018. 4. 15..
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class MergeSortTest {

    private MergeSort mergeSort = new MergeSort();

    @Test
    public void testCase1_normal() {
        testcase(IntStream.of(12, 35, 87, 26, 9, 28, 7), IntStream.of(7, 9, 12, 26, 28, 35, 87));
    }

    @Test
    public void testCase2_if_has_one_item() {
        testcase(IntStream.of(1), IntStream.of(1));
    }

    @Test
    public void testCase3_if_sorted() {
        testcase(IntStream.of(1, 2, 3), IntStream.of(1, 2, 3));
    }


    @Test
    public void testCase4_10000_items() {
        //Given
        List<Integer> randomItems = new ArrayList<>();
        for (int count = 0; count < 10000; count++) {
            randomItems.add((int) (Math.random() * 10000));
        }

        //When
        LocalTime startTime = LocalTime.now();
        mergeSort.sort(randomItems);
        LocalTime endTime = LocalTime.now();

        log.info("time: {} ms", Duration.between(startTime, endTime).toMillis());
    }

    @Test
    public void testCase5_10000_sorted_items() {
        //Given
        List<Integer> randomItems = new ArrayList<>();
        for (int count = 0; count < 10000; count++) {
            randomItems.add(count);
        }

        //When
        LocalTime startTime = LocalTime.now();
        mergeSort.sort(randomItems);
        LocalTime endTime = LocalTime.now();

        log.info("time: {} ms", Duration.between(startTime, endTime).toMillis());
    }

    private void testcase(IntStream originalStream, IntStream expectedSteam) {
        //Given
        List<Integer> originals = originalStream.boxed().collect(Collectors.toList());
        List<Integer> expected = expectedSteam.boxed().collect(Collectors.toList());

        //When
        List<Integer> actual = mergeSort.sort(originals);

        //Assert
        assertTrue(actual.equals(expected));
    }
}