package com.study.algorithm.problem;

import java.util.*;

public class RetireProblem {

    public static void main(String... arg) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Task[] tasks = new Task[n + 1];

        int lastIndex = 0;
        for (int day = 1; day <= n; day++) {
            int duration = scanner.nextInt();
            int money = scanner.nextInt();

            Task task = new Task(day, duration, money);

            if (task.lastDay <= n) {
                tasks[day - 1] = task;
                lastIndex = day;
            }
        }

        Map<Integer, Integer> profitMap = new HashMap<>();
        Map<Integer, Integer> positionMap = new HashMap<>();
        Map<Integer, Integer> powMap = new HashMap<>();

        for (int i = 1; i < (1 << lastIndex); i++) {
            int current = Integer.highestOneBit(i);
            int sub = i & ~current;

            int sum = profitMap.getOrDefault(sub, 0);
            int to = positionMap.getOrDefault(sub, 0);

            int position = powMap.containsKey(current)? powMap.get(current) : getPowNumber(current, powMap);
            Task task = tasks[position];

            if (Objects.nonNull(task) && task.startDay > to) {
                to = task.lastDay;
                sum += task.money;
            }

            profitMap.put(i, sum);
            positionMap.put(i, to);
        }

        System.out.println(profitMap.values().stream().max(Integer::compareTo).orElse(0));
    }

    private static int getPowNumber(int number, Map<Integer, Integer> powMap) {
        int pow = 0;
        while((number = number / 2) > 0) {
            pow ++;
        }

        powMap.put(number, pow);
        return pow;
    }

    public static class Task {
        public int startDay;
        public int lastDay;
        public int duration;
        public int money;

        public Task(int startDay, int duration, int money) {
            this.startDay = startDay;
            this.duration = duration;
            this.money = money;
            this.lastDay = startDay + duration - 1;
        }
    }
}
