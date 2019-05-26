package com.study.algorithm.code.festival.kakato2018;

import java.util.Scanner;

public class Doll {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] inputs = new int[n];
        for (int index = 0; index < n; index++) {
            inputs[index] = scanner.nextInt();
        }

        double min = Double.MAX_VALUE;

        for (int count = k; count <= n; count++) {
            for (int index = 0; index <= n - count; index++) {
                int sum = 0;
                for (int position = index; position < index + count; position++) {
                    sum += inputs[position];
                }

                double mean = (double) sum / (double) count;

                double middleSum = 0;
                for (int position = index; position < index + count; position++) {
                    middleSum += Math.pow(inputs[position] - mean, 2);
                }

                double standardDeviation = middleSum / count;
                min = Math.min(min, standardDeviation);
            }
        }

        System.out.println(Math.sqrt(min));
    }
}
