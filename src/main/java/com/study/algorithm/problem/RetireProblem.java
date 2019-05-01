package com.study.algorithm.problem;

import java.util.*;

public class RetireProblem {

    public static void main(String... arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] time = new int[n + 2];
        int[] profit = new int[n + 2];

        for (int day = 1; day <= n; day++) {
            int duration = scanner.nextInt();
            int money = scanner.nextInt();
            int lastDay = day + duration - 1;

            if (lastDay <= n) {
                time[day] = duration;
                profit[day] = money;
            }
        }

        int dp[] = new int[n + 6];
        int max = 0;
        for (int index = 1; index <= n + 1; index++) {
            dp[index] = Math.max(dp[index], max);
            dp[time[index] + index] = Math.max(dp[time[index] + index], dp[index] + profit[index]);
            max = Math.max(dp[index], max);
        }

        System.out.println(max);
    }
}
