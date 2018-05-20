package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 5. 20..
 */
public class LottoProblem {
    private static final Integer LOTTO_NUM_CNT = 6;
    private static final String EMPTY = "";
    private static final String SPACE = " ";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int selectedCount;
        while ((selectedCount = scanner.nextInt()) != 0) {
            int[] items = new int[selectedCount];

            for (int index = 0; index < selectedCount; index++) {
                items[index] = scanner.nextInt();
            }

            printLotto(items, 0, 0, EMPTY);
            System.out.println(EMPTY);
        }

        scanner.close();
    }

    public static void printLotto(int[] items, int depth, int position, String lotto) {
        if (depth == LOTTO_NUM_CNT) {
            System.out.println(lotto);
            return;
        }

        for (int index = position; index < items.length; index++) {
            if (items.length - index < LOTTO_NUM_CNT - depth) {
                return;
            }

            printLotto(items, depth + 1, index + 1, lotto + items[index] + SPACE);
        }
    }
}
