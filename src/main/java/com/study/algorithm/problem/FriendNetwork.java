package com.study.algorithm.problem;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FriendNetwork {
    private static final int MAX_INDEX = 100_000 + 1;
    private static int serial = -1;

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();

        for (int tc = 0; tc < testCount; tc++) {
            Map<String, Integer> idMap = new HashMap<>();
            int[] parent = new int[MAX_INDEX];
            int[] rank = new int[MAX_INDEX];
            int[] count = new int[MAX_INDEX];

            init(parent, count);

            int friendship = scanner.nextInt();

            for (int index = 0; index < friendship; index++) {
                String xName = scanner.next();
                String yName = scanner.next();

                int x = calculateId(idMap, xName);
                int y = calculateId(idMap, yName);

                union(parent, rank, count, x, y);

                int root = find(parent, x);

                System.out.println(count[root]);
            }
        }
    }

    public static int calculateId(Map<String, Integer> map, String str) {
        if (!map.containsKey(str)) {
            map.put(str, ++serial);
        }

        return map.get(str);
    }

    private static void init(int[] parent, int[] count) {
        serial = -1;

        for (int index = 0; index < MAX_INDEX; index++) {
            parent[index] = index;
            count[index] = 1;
        }
    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent, parent[x]);
    }

    private static void union(int[] parent, int[] rank, int[] count, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (x == y) {
            return;
        }

        int total_cnt = count[x] + count[y];

        if (rank[x] < rank[y]) {
            parent[x] = y;
            count[y] = total_cnt;
        } else {
            parent[y] = x;
            count[x] = total_cnt;
        }

        if (rank[x] == rank[y]) {
            rank[x]++;
            count[x] = total_cnt;
        }
    }
}
