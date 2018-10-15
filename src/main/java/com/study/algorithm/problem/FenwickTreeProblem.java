package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 10. 15..
 */
public class FenwickTreeProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int inputCount = scanner.nextInt();
        int modifyCount = scanner.nextInt();
        int testcase = scanner.nextInt();

        int size = getFloorPower2(inputCount) + 1;
        long[] values = new long[size];
        long[] trees = new long[size];

        //save input data to array
        for (int index = 1; index <= inputCount; index++) {
            values[index] = scanner.nextInt();
        }

        //calculate sum ( 1 ~ index )
        for (int index = 1; index < size; index++) {
            long partitionSum = 0;

            for (int position = index; position > index - (index & -index); position--) {
                partitionSum += values[position];
            }

            trees[index] = partitionSum;
        }

        //modify value or calculate partition sum ( from ~ to )
        int testTry = 0;
        long[] answers = new long[testcase];

        for (int index = 0; index < modifyCount + testcase; index++) {
            switch (scanner.nextInt()) {
                case 1:
                    int position = scanner.nextInt();
                    int updateValue = scanner.nextInt();
                    update(trees, position, updateValue - values[position]);
                    values[position] = updateValue;
                    break;

                case 2:
                    int from = scanner.nextInt();
                    int to = scanner.nextInt();
                    answers[testTry++] = sum(trees, to) - sum(trees, from - 1);
                    break;
            }
        }

        //print answer
        for (int index = 0; index < testcase; index++) {
            System.out.println(answers[index]);
        }
    }

    private static int getFloorPower2(int number) {
        int floor = 1;
        while (number > floor) {
            floor <<= 1;
        }
        return floor;
    }

    private static void update(long[] array, int index, long diff) {
        while (index < array.length) {
            array[index] += diff;
            index += (index & -index);
        }
    }

    public static long sum(long[] array, int to) {
        long sum = 0;

        while (to > 0) {
            sum += array[to];
            to -= (to & -to);
        }

        return sum;
    }
}
