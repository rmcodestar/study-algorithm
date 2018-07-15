package com.study.algorithm.problem;

import java.util.*;

/**
 * Created by rmcodestar on 2018. 7. 15..
 */
public class RollCakeProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cakeLength = scanner.nextInt();
        int personCount = scanner.nextInt();

        int[] rollCake = new int[cakeLength + 1];

        int expectedMaxIndex = 1;
        int expectedMaxCount = 0;
        for (int index = 1; index <= personCount; index++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int count = end - start + 1;

            if (expectedMaxCount < count) {
                expectedMaxIndex = index;
                expectedMaxCount = count;
            }

            for (int position = start; position <= end; position++) {
                if (rollCake[position] == 0) {
                    rollCake[position] = index;
                }
            }
        }

        Map<Integer, Integer> counter = new HashMap<>();
        int actualMaxCount = 0;
        int actualMaxIndex = 1;

        for (int position = 1; position <= cakeLength; position++) {
            if (rollCake[position] == 0) {
                continue;
            }

            int personIndex = rollCake[position];
            int count = counter.getOrDefault(personIndex, 0);
            int nextCount = count + 1;

            counter.put(personIndex, nextCount);

            if (nextCount > actualMaxCount) {
                actualMaxCount = nextCount;
                actualMaxIndex = personIndex;
            }
        }

        System.out.println(expectedMaxIndex);
        System.out.println(actualMaxIndex);
    }

}
