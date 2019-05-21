package com.study.algorithm.problem;

import java.util.Scanner;

public class FixLadder {
    public static int output = -1;

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int ladderCount = scanner.nextInt();
        int height = scanner.nextInt();

        boolean[][] ladders = new boolean[height][number - 1];

        for (int index = 0; index < ladderCount; index++) {
            int y = scanner.nextInt();
            int x = scanner.nextInt();

            ladders[y - 1][x - 1] = true;

        }

        if (isCompleted(ladders)) {
            System.out.println(0);
            return;
        }

        //nC1
        for (int max = 1; max <= 3; max++) {
            fix(ladders, max, 0, 0);

            if (output != -1) {
                System.out.println(max);
                return;
            }
        }


        System.out.println(-1);
    }


    public static void fix(boolean[][] ladders, int max, int current, int index) {
        if (current >= max) {
            if (isCompleted(ladders)) {
                output = max;
            }

            return;
        }

        int x = index % ladders[0].length;
        int y = index / ladders[0].length;

        for (int j = y; j < ladders.length; j++) {

            int start = (j == y) ? x : 0;
            for (int i = start; i < ladders[0].length; i++) {
                if (ladders[j][i] || (i - 1 >= 0 && ladders[j][i - 1]) || (i + 1 < ladders[0].length && ladders[j][i + 1])) {
                    continue;
                }

                ladders[j][i] = true;
                fix(ladders, max, current + 1, i + j * ladders[0].length);
                ladders[j][i] = false;
            }
        }
    }

    public static boolean isCompleted(boolean[][] ladders) {
        // setup
        int sizeX = ladders[0].length;
        int sizeY = ladders.length;

        int[] outputs = new int[sizeX + 1];

        for (int x = 0; x < outputs.length; x++) {
            outputs[x] = x;
        }

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                if (ladders[y][x]) {
                    swap(outputs, x, x + 1);
                }
            }
        }

        boolean isCompleted = true;

        for (int x = 0; x < outputs.length; x++) {
            if (outputs[x] != x) {
                isCompleted = false;
                break;
            }
        }

        return isCompleted;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static class Position {
        int number;
        int height;

        public Position(int x, int y) {
            this.number = x;
            this.height = y;
        }
    }
}
