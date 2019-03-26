package com.study.algorithm.game;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sudoku {
    private static final int SIZE = 9;
    private static final int EMPTY_VALUE = 0;
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final Set<Integer> FULL_SET = new HashSet<>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));

    public class Point {
        int x;
        int y;
        List<Integer> candidates = new ArrayList<>();
        int value;
        int areaNo;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.areaNo = (x / 3) + (y / 3) * 3;
        }

        public int getAreaNo() {
            return areaNo;
        }

        public int getValue() {
            return value;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public int[][] solve(int[][] input) {
        int[][] output = Arrays.stream(input).map(r -> r.clone()).toArray(int[][]::new);
        List<Point> points = makePoints(input);

        print(input);

        int turn = 1;
        List<Point> finding;

        do {
            finding = new ArrayList<>();

            List<Point> f1 = findInSection(points, output);
            List<Point> f2 = findInRow(points, output);
            List<Point> f3 = findUsingCountingInSection(points, output);

            System.out.println("[turn] " + turn + ", finding : " + (f1.size() + f2.size() + f3.size()));

            finding.addAll(f1);
            finding.addAll(f2);
            finding.addAll(f3);

            print(output);

        } while (turn++ < 100 && !finding.isEmpty());

        return output;
    }

    private List<Point> makePoints(int[][] input) {
        List<Point> points = new ArrayList<>();

        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                points.add(new Point(x, y, input[y][x]));
            }
        }

        return points;
    }

    private List<Point> findInSection(List<Point> points, int[][] output) {
        List<Point> finding = new ArrayList<>();

        Map<Integer, List<Point>> sectionPointMap = points.stream()
                .collect(Collectors.groupingBy(Point::getAreaNo));

        for (int areaNo : sectionPointMap.keySet()) {
            List<Integer> numbers = sectionPointMap.get(areaNo)
                    .stream()
                    .filter(point -> point.value != EMPTY_VALUE)
                    .map(Point::getValue)
                    .collect(Collectors.toList());

            List<Integer> candidates = FULL_SET.stream()
                    .filter(item -> !numbers.contains(item))
                    .collect(Collectors.toList());

            sectionPointMap.get(areaNo)
                    .stream()
                    .filter(point -> point.value == EMPTY_VALUE)
                    .forEach(point -> point.candidates = new ArrayList<>(candidates));


            if (candidates.size() == 1) {
                Optional<Point> optionalPoint = sectionPointMap.get(areaNo)
                        .stream()
                        .filter(point -> point.value == EMPTY_VALUE)
                        .findFirst();

                if (optionalPoint.isPresent()) {
                    Point point = optionalPoint.get();
                    determineValue(point, candidates.get(0), points, output);
                    finding.add(point);

                } else {
                    throw new IllegalStateException("invalid case, not matched, areaNo : " + areaNo + ", candidate : " + candidates.get(0));
                }

            }
        }

        return finding;
    }

    private List<Point> findInRow(List<Point> points, int[][] output) {
        List<Point> finding = new ArrayList<>();

        for (Point point : points) {
            List<Integer> candidates = point.candidates;

            if (point.value != EMPTY_VALUE) {
                continue;
            }


            for (int y = 0; y < SIZE; y++) {
                int value = output[y][point.x];
                if (value != EMPTY_VALUE && candidates.contains(value)) {
                    candidates.remove(Integer.valueOf(value));
                }
            }

            for (int x = 0; x < SIZE; x++) {
                int value = output[point.y][x];
                if (value != EMPTY_VALUE && candidates.contains(value)) {
                    candidates.remove(Integer.valueOf(value));
                }
            }

            if (candidates.size() == 1) {
                determineValue(point, candidates.get(0), points, output);
                finding.add(point);
            }
        }

        return finding;
    }

    private void determineValue(Point point, Integer candidate, List<Point> points, int[][] output) {
        point.value = candidate;
        output[point.y][point.x] = candidate;

        if (point.candidates.contains(candidate)) {
            point.candidates.remove(candidate);
        }

        points.stream()
                .filter(otherPoint -> otherPoint.x == point.x || otherPoint.y == point.y || otherPoint.areaNo == point.areaNo)
                .forEach(otherPoint -> {
                    if (otherPoint.candidates.contains(candidate)) {
                        otherPoint.candidates.remove(candidate);
                    }
                });
    }

    private List<Point> findUsingCountingInSection(List<Point> points, int[][] output) {
        List<Point> finding = new ArrayList<>();

        Map<Integer, List<Point>> sectionPointMap = points.stream()
                .collect(Collectors.groupingBy(Point::getAreaNo));

        for (int areaNo : sectionPointMap.keySet()) {
            Map<Integer, Long> countMap = sectionPointMap.get(areaNo)
                    .stream()
                    .filter(point -> point.value == EMPTY_VALUE)
                    .map(point -> point.candidates)
                    .flatMap(integers -> integers.stream())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            for (Integer candidate : countMap.keySet()) {
                if (candidate == 0L) {
                    continue;
                }

                if (countMap.get(candidate) == 1L) {
                    Optional<Point> optionalPoint = sectionPointMap.get(areaNo)
                            .stream()
                            .filter(point -> point.value == EMPTY_VALUE)
                            .filter(point -> point.candidates.contains(candidate))
                            .findFirst();

                    if (optionalPoint.isPresent()) {
                        Point point = optionalPoint.get();
                        determineValue(point, candidate, points, output);
                        finding.add(point);

                    } else {
                        throw new IllegalStateException("invalid case");
                    }
                }
            }

        }

        return finding;
    }


    private void print(int[][] input) {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {

                StringBuilder builder = new StringBuilder();

                if (input[y][x] != 0) {
                    builder.append(ANSI_CYAN);
                }

                builder.append(input[y][x] + " ");

                if (input[y][x] != 0) {
                    builder.append(ANSI_RESET);
                }

                System.out.print(builder.toString());

                if (x % 3 == 2 && x != SIZE - 1) {
                    System.out.print("| ");
                }

            }

            System.out.println();

            if (y % 3 == 2) {
                System.out.println("----------------------");
            }

        }
    }
}
