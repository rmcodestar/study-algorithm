package com.study.algorithm.problem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 10. 31..
 */
public class LCA {
    private static final int INITIAL = 0;

    public static class Pair {
        public Pair(int a, int b) {
            this.item = a;
            this.other = b;
        }

        public int item;
        public int other;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodeCount = scanner.nextInt();

        int[] depth = new int[nodeCount + 1];
        int[] parent = new int[nodeCount + 1];

        depth[1] = 1;

        Queue<Pair> remained = new LinkedList<>();

        for (int index = 1; index < nodeCount; index++) {
            int item = scanner.nextInt();
            int other = scanner.nextInt();

            remained.offer(new Pair(item, other));
        }

        while (!remained.isEmpty()) {
            Pair pair = remained.poll();

            if (depth[pair.item] != INITIAL) {
                parent[pair.other] = pair.item;
                depth[pair.other] = depth[pair.item] + 1;

            } else if (depth[pair.other] != INITIAL) {
                parent[pair.item] = pair.other;
                depth[pair.item] = depth[pair.other] + 1;

            } else {
                remained.offer(pair);
            }
        }

        int testcase = scanner.nextInt();
        int[] answers = new int[testcase];

        for (int index = 0; index < testcase; index++) {
            int node = scanner.nextInt();
            int otherNode = scanner.nextInt();

            while (node != otherNode) {
                if (depth[node] == depth[otherNode]) {
                    node = parent[node];
                    otherNode = parent[otherNode];

                } else if (depth[node] > depth[otherNode]) {
                    node = parent[node];

                } else {
                    otherNode = parent[otherNode];
                }
            }

            answers[index] = node;
        }

        for (int index = 0; index < testcase; index++) {
            System.out.println(answers[index]);
        }
    }
}
