package com.study.algorithm.problem;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class HistogramMaxArea {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            int[] inputs = Arrays.stream(line.split(" "))
                    .mapToInt(i -> Integer.valueOf(i))
                    .toArray();

            if (inputs[0] == 0) {
                return;
            }

            int n = inputs[0];

            int[] heights = new int[n + 1];

            for (int index = 1; index <= n; index++) {
                heights[index - 1] = inputs[index];
            }

            double maxArea = 0;

            Stack<Integer> stack = new Stack<>();

            for (int right = 0; right <= n; right++) {

                while (!stack.isEmpty() && heights[stack.peek()] > heights[right]) {
                    double y = heights[stack.peek()];

                    stack.pop();

                    double x = stack.isEmpty() ? right : right - stack.peek() - 1;

                    maxArea = Math.max(maxArea, x * y * 1.0);
                }

                stack.push(right);
            }

            System.out.println(String.format("%.0f", maxArea));

        }
    }
}
