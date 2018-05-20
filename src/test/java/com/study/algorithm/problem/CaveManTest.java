package com.study.algorithm.problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.Description;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by rmcodestar on 2018. 5. 13..
 */
public class CaveManTest {
    private static int[] input_200_000;

    @BeforeClass
    public static void setup() {
        input_200_000 = new int[200000];
        Arrays.fill(input_200_000, 1);
    }

    @Test(timeout = 1000)
    public void testcase1_200_000_animals_is_one() {
        assertEquals(getTorchlight(input_200_000), 200000);
    }

    @Test
    public void testcase_all_one() {
        assertEquals(getTorchlight(new int[]{1, 1, 1, 1, 1}), 5);
    }

    @Test(timeout = 1000)
    public void testcase2() {
        assertEquals(getTorchlight(new int[]{1, 2, 2, 1, 3, 1, 1, 2, 4}), 3);
    }

    @Test(timeout = 1000)
    public void testcase3() {
        assertEquals(getTorchlight(new int[]{1, 2, 3, 2, 1, 2, 1}), 2);
    }

    @Test(timeout = 1000)
    public void testcase4() {
        assertEquals(getTorchlight(new int[]{6, 1, 1, 1, 3, 1, 1}), 2);
    }

    private int getTorchlight(int[] animals) {
        Map<Integer, Animal> map = new HashMap<>();
        for (int index = 0; index < animals.length; index++) {
            int start = Math.max(0, index - animals[index] + 1);
            int end = Math.min(animals.length - 1, index + animals[index] - 1);

            Animal animal = new Animal(start, end, animals[index], index);

            for (int position = animal.getStart(); position <= animal.getEnd(); position++) {
                Animal max = map.get(position);

                if (Objects.nonNull(max)) {
                    if (max.getEnd() < animal.getEnd()) {
                        map.put(position, animal);
                    }
                } else {
                    map.put(position, animal);
                }
            }
        }

        int reached = 0;
        int count = 0;

        while (reached <= animals.length - 1) {
            reached = map.get(reached).getEnd() + 1;
            count++;
        }

        return count;
    }

    @Data
    @AllArgsConstructor
    class Animal {
        private int start;
        private int end;
        private int size;
        private int position;

    }
}
