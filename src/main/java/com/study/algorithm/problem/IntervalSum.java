package com.study.algorithm.problem;

import java.util.Scanner;

public class IntervalSum {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int treeSize = calculateTreeSize(n);

        int[] inputs = new int[n + 1];
        double[] nodes = new double[treeSize + 1];
        double[] lazys = new double[treeSize + 1];

        for (int index = 1; index <= n; index++) {
            inputs[index] = scanner.nextInt();
        }

        makeTree(nodes, inputs, 1, 1, n);

        for (int index = 0; index < m + k; index++) {
            int operation = scanner.nextInt();

            int from = scanner.nextInt();
            int to = scanner.nextInt();

            switch (operation) {
                case 1:
                    double diff = scanner.nextDouble();
                    update(nodes, lazys, 1, 1, n, from, to, diff);
                    break;

                case 2:
                    double sum = sum(nodes, lazys, 1, 1, n, from, to);
                    System.out.println(String.format("%.0f", sum));
                    break;
            }

        }

    }

    public static void update(double[] nodes, double[] lazys, int nodeIndex, int start, int end, int from, int to, double diff) {
        propagationLazy(nodes, lazys, nodeIndex, start, end);

        if (end < from || to < start) {
            return;
        }

        if (from <= start && end <= to) {
            nodes[nodeIndex] += (end - start + 1) * diff;

            if (start != end) {
                lazys[2 * nodeIndex] += diff;
                lazys[2 * nodeIndex + 1] += diff;
            }

            return;
        }

        int middle = (start + end) / 2;

        update(nodes, lazys, 2 * nodeIndex, start, middle, from, to, diff);
        update(nodes, lazys, 2 * nodeIndex + 1, middle + 1, end, from, to, diff);

        nodes[nodeIndex] = nodes[2 * nodeIndex] + nodes[2 * nodeIndex + 1];
    }

    public static void propagationLazy(double[] nodes, double[] lazys, int nodeIndex, int start, int end) {
        if (lazys[nodeIndex] != 0) { //음수도 가능..
            nodes[nodeIndex] += (end - start + 1) * lazys[nodeIndex];

            if (start != end) {
                lazys[2 * nodeIndex] += lazys[nodeIndex];
                lazys[2 * nodeIndex + 1] += lazys[nodeIndex];
            }

            lazys[nodeIndex] = 0;
        }
    }

    public static double sum(double[] nodes, double[] lazys, int nodeIndex, int start, int end, int from, int to) {
        propagationLazy(nodes, lazys, nodeIndex, start, end);

        if (end < from || to < start) {
            return 0;
        }

        if (from <= start && end <= to) {
            return nodes[nodeIndex];
        }

        int middle = (start + end) / 2;

        double leftSum = sum(nodes, lazys, 2 * nodeIndex, start, middle, from, to);
        double rightSum = sum(nodes, lazys, 2 * nodeIndex + 1, middle + 1, end, from, to);

        return leftSum + rightSum;
    }

    public static double makeTree(double[] nodes, int[] inputs, int nodeIndex, int start, int end) {
        if (start == end) {
            nodes[nodeIndex] = inputs[start];
            return nodes[nodeIndex];
        }

        int middle = (start + end) / 2;

        double left = makeTree(nodes, inputs, 2 * nodeIndex, start, middle);
        double right = makeTree(nodes, inputs, 2 * nodeIndex + 1, middle + 1, end);

        nodes[nodeIndex] = left + right;

        return nodes[nodeIndex];
    }


    public static int calculateTreeSize(int n) {
        int index = 0;
        while (n > Math.pow(2, index)) {
            index++;
        }

        return (int) Math.pow(2, index) * 2;
    }
}
