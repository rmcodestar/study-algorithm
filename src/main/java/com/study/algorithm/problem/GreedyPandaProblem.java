package com.study.algorithm.problem;

import java.util.*;

public class GreedyPandaProblem {
    private static final int INIIALIZED = -1;

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[][] inputs = new int[size][size];
        int[][] dp = new int[size][size];


        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                inputs[y][x] = scanner.nextInt();
                dp[y][x] = INIIALIZED;
            }
        }

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                doRecursive(x, y, inputs, dp);
            }
        }

        int max = 1;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                max = Math.max(dp[y][x], max);
            }
        }

        System.out.println(max);
    }

    private static int doRecursive(int x, int y, int[][] inputs, int[][] dp) {
        if (dp[y][x] != INIIALIZED) {
            return dp[y][x];
        }

        dp[y][x] = 1;

        if (x + 1 < inputs.length && inputs[y][x + 1] > inputs[y][x]) {
            dp[y][x] = Math.max(dp[y][x], doRecursive(x + 1, y, inputs, dp) + 1);
        }

        if (y + 1 < inputs.length && inputs[y + 1][x] > inputs[y][x]) {
            dp[y][x] = Math.max(dp[y][x], doRecursive(x, y + 1, inputs, dp) + 1);
        }

        if (x - 1 >= 0 && inputs[y][x - 1] > inputs[y][x]) {
            dp[y][x] = Math.max(dp[y][x], doRecursive(x - 1, y, inputs, dp) + 1);
        }

        if (y - 1 >= 0 && inputs[y - 1][x] > inputs[y][x]) {
            dp[y][x] = Math.max(dp[y][x], doRecursive(x, y - 1, inputs, dp) + 1);
        }

        return dp[y][x];
    }
}
