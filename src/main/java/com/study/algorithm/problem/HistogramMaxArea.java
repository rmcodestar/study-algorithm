package com.study.algorithm.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class HistogramMaxArea {

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            int n = Integer.parseInt(stringTokenizer.nextToken());

            if (n == 0) {
                return;
            }

            List<Integer> inputs = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                inputs.add(Integer.parseInt(stringTokenizer.nextToken()));
            }

            inputs.add(0);

            Stack<Integer> stack = new Stack<>();
            double maxArea = 0;

            for (int right = 0; right <= n; right++) {
                while (!stack.isEmpty() && inputs.get(stack.peek()) > inputs.get(right)) {
                    int y = inputs.get(stack.peek());

                    stack.pop();

                    int x = stack.isEmpty() ? right : right - stack.peek() - 1;

                    maxArea = Math.max(maxArea, (double) x * y);
                }

                stack.push(right);
            }

            System.out.println(String.format("%.0f", maxArea));

        }
    }
}
