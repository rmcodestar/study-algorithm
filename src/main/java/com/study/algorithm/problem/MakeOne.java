package com.study.algorithm.problem;

import java.util.Scanner;

public class MakeOne {
    private static final String SPACE = " ";

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] dp = new int[Math.max(4, N + 1)];
        String[] paths = new String[Math.max(4, N + 1)];

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        paths[1] = "1";
        paths[2] = "2 1";
        paths[3] = "3 1";

        for (int i = 4; i <= N; i++) {
            int min = Integer.MAX_VALUE;
            String path = "";

            if (dp[i - 1] > 0) {
                if (min > dp[i - 1] + 1) {
                    min = dp[i - 1] + 1;
                    path = i + SPACE + paths[i - 1];
                }
            }

            if (i % 2 == 0) {
                if (min > dp[i / 2] + 1) {
                    min = dp[i / 2] + 1;
                    path = i + SPACE + paths[i / 2];
                }
            }

            if (i % 3 == 0) {
                if (min > dp[i / 3] + 1) {
                    min = dp[i / 3] + 1;
                    path = i + SPACE + paths[i / 3];
                }
            }

            dp[i] = min;
            paths[i] = path;
        }

        System.out.println(dp[N]);
        System.out.println(paths[N]);
    }
}
