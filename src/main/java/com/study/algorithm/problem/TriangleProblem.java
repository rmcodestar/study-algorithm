package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 5. 20..
 */
public class TriangleProblem {

    public static void main(String[] args) {
        int max = 0;
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        int[][] array = new int[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x <= y; x++) {
                array[y - x][x] = scanner.nextInt();
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                if (y == 0) {
                    array[y][x] += array[y][x - 1];

                } else if (x == 0) {
                    array[y][x] += array[y - 1][x];

                } else {
                    array[y][x] += Math.max(array[y - 1][x], array[y][x - 1]);
                }
            }
        }

        for (int x = 0; x < size; x++) {
            max = Math.max(array[size - 1][x], max);
        }

        System.out.println(max);
    }
}
