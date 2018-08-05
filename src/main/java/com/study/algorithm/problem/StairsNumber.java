package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 7. 30..
 */
public class StairsNumber {
    private static final int MOD_NUMBER = 1000000000;
    private static int[] dp = new int[101];
    private static int FULL = 1023;
    private static long[][][] calculated = new long[101][10][1024];

    /*
        //10: 1
        //11 : 3
        //12 : 14
        //13 : 37
        //14 : 117
        //15 : 287

     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();

        for (int index = 0; index <= 9; index++) {
            calculated[1][index][1 << index] = 1;
        }

        for (int current = 2; current <= length; current++) {
            for (int index = 0; index <= 9; index++) {
                for (int flag = 0; flag <= FULL; flag++) {
                    if (index == 0) {
                        calculated[current][0][flag | (1 << 0)] += calculated[current - 1][1][flag];
                        calculated[current][0][flag | (1 << 0)] %= MOD_NUMBER;

                    } else if (index == 9) {
                        calculated[current][9][flag | (1 << 9)] += calculated[current - 1][8][flag];
                        calculated[current][9][flag | (1 << 9)] %= MOD_NUMBER;

                    } else {
                        calculated[current][index][flag | (1 << index)] += calculated[current - 1][index + 1][flag];
                        calculated[current][index][flag | (1 << index)] %= MOD_NUMBER;
                        calculated[current][index][flag | (1 << index)] += calculated[current - 1][index - 1][flag];
                        calculated[current][index][flag | (1 << index)] %= MOD_NUMBER;
                    }
                }
            }
        }

        long sum = 0;
        for (int index = 1; index < 10; index++) {
            sum = (sum + calculated[length][index][FULL]) % MOD_NUMBER;
        }

        System.out.println(sum);

    }

    private static void process(String input, int length, int bitmask) {
        if (input.length() == length) {
            if (bitmask == 1023) {
                System.out.println("[" + length + "] " + input);
                dp[length] = (dp[length] + 1) % MOD_NUMBER;
            }

            return;
        }

        int number = Integer.valueOf(input.substring(input.length() - 1, input.length()));
        int afterNumber = number + 1;
        int beforeNumber = number - 1;
        if (number == 0) {
            process(input + String.valueOf(afterNumber), length, bitmask | 1 << afterNumber);

        } else if (number == 9) {
            process(input + String.valueOf(beforeNumber), length, bitmask | 1 << beforeNumber);

        } else {
            process(input + String.valueOf(afterNumber), length, bitmask | 1 << afterNumber);
            process(input + String.valueOf(beforeNumber), length, bitmask | 1 << beforeNumber);
        }
    }
}
