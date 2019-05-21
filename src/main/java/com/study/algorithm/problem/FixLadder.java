package com.study.algorithm.problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FixLadder {
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

        if (isCompleted(ladders, new ArrayList<>())) {
            System.out.println(0);
            return;
        }

        //nC1
        for (int y = 0; y < ladders.length; y++) {
            for (int x = 0; x < ladders[0].length; x++) {
                if (ladders[y][x]) {
                    continue;
                }

                if (!(x - 1 >= 0 && ladders[y][x - 1]) || !(x + 1 < ladders[0].length && ladders[y][x + 1])) {
                    boolean isCompleted = isCompleted(ladders, Arrays.asList(new Position(x, y)));

                    if (isCompleted) {
                        System.out.println(1);
                        return;
                    }
                }

            }
        }

        //nC2

        //nC3

        System.out.println(-1);
    }

    public void getPositition(boolean[][] ladders, List<Position> candidates, int count) {
        if (count == 0) {
            return;
        }
    }


    public static boolean isCompleted(boolean[][] ladders, List<Position> newLadders) {
        // setup
        for (Position position : newLadders) {
            ladders[position.height][position.number] = true;
        }

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

        for (Position position : newLadders) {
            ladders[position.height][position.number] = false;
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
