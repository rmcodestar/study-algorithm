package com.study.algorithm.code.festival.kakato2018;

import java.util.Scanner;

public class RewardHunter {
    private static int[][] REWARD_RULES_2017 = {{0, 0}, {5_000_000, 1}, {3_000_000, 2}, {2_000_000, 3}, {500_000, 4}, {300_000, 5}, {100_000, 6}};
    private static int[][] REWARD_RULES_2018 = {{0, 0}, {5_120_000, 1}, {2_560_000, 2}, {1_280_000, 4}, {640_000, 8}, {320_000, 16}};

    public static void main(String... args) {
        int[] reward2017 = new int[101];
        int[] reward2018 = new int[100];

        Scanner scanner = new Scanner(System.in);
        int testcase = scanner.nextInt();

        int[][] testcases = new int[testcase][2];

        for (int index = 0; index < testcase; index++) {
            testcases[index][0] = scanner.nextInt();
            testcases[index][1] = scanner.nextInt();
        }

        calculateRewardAmount(REWARD_RULES_2017, reward2017);
        calculateRewardAmount(REWARD_RULES_2018, reward2018);

        for (int index = 0; index < testcase; index++) {
            System.out.println(reward2017[testcases[index][0]] + reward2018[testcases[index][1]]);
        }
    }

    public static void calculateRewardAmount(int[][] rules, int[] rewards) {
        int current = rules[1][1];
        int index = 1;

        do {
            rewards[index] = rules[current][0];

            if (--rules[current][1] == 0) {
                current++;
            }

            index++;

        } while (current < rules.length);
    }
}
