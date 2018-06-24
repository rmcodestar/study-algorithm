package com.study.algorithm.problem;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 6. 24..
 */
public class BarnProblem {
    private static int NO_COW = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cowCount = scanner.nextInt();
        int barnCount = scanner.nextInt();
        boolean[][] edge = new boolean[cowCount][barnCount];

        for (int index = 0; index < cowCount; index++) {
            int preferenceCount = scanner.nextInt();

            for (int otherIndex = 0; otherIndex < preferenceCount; otherIndex++) {
                edge[index][scanner.nextInt() - 1] = true;
            }
        }

        int[] currentBarn = new int[barnCount];
        Arrays.fill(currentBarn, NO_COW);

        int count = 0;

        for (int cowIndex = 0; cowIndex < cowCount; cowIndex++) {
            boolean[] visited = new boolean[cowCount];
            Arrays.fill(visited, false);

            if (enableEnterBarn(cowIndex, edge, visited, currentBarn)) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static boolean enableEnterBarn(int cowIndex, boolean[][] edge, boolean[] visited, int[] currentBarn) {
        if (visited[cowIndex]) {
            return false;
        }

        visited[cowIndex] = true;

        for (int barnIndex = 0; barnIndex < currentBarn.length; barnIndex++) {
            if (edge[cowIndex][barnIndex]) {

                if (currentBarn[barnIndex] == NO_COW) {
                    currentBarn[barnIndex] = cowIndex;
                    return true;

                } else {
                    int beforeCow = currentBarn[barnIndex];

                    if (enableEnterBarn(beforeCow, edge, visited, currentBarn)) {
                        currentBarn[barnIndex] = cowIndex;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
