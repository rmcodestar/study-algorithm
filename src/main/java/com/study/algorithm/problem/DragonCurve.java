package com.study.algorithm.problem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DragonCurve {
    private static final int MAX_SIZE = 101;

    public static void main(String... args) {
        boolean[][] isDragonCurve = new boolean[MAX_SIZE][MAX_SIZE];
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for (int index = 0; index < n; index++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Direction direction = Direction.of(scanner.nextInt());
            int generation = scanner.nextInt();

            Point startPoint = new Point(x, y);
            Point endPoint = getNextPoint(startPoint, direction);

            List<Point> points = new ArrayList<>();
            points.add(startPoint);
            points.add(endPoint);

            List<Point> curves = findDragonCurve(points, generation);
            markPoints(isDragonCurve, curves);
        }

        System.out.println(getSquarePointCount(isDragonCurve));
    }

    private static Point getNextPoint(Point point, Direction direction) {
        switch (direction) {
            case TOP:
                return new Point(point.getX(), point.getY() + 1);
            case BOTTOM:
                return new Point(point.getX(), point.getY() - 1);
            case LEFT:
                return new Point(point.getX() - 1, point.getY());
            case RIGHT:
                return new Point(point.getX() + 1, point.getY());
        }

        throw new IllegalArgumentException("invalid direction, direction : " + direction);
    }

    private static List<Point> findDragonCurve(List<Point> points, int generation) {
        List<Point> curves = new ArrayList<>();

        for (Point point : points) {
            curves.add(point);
        }

        for (int tryCount = 0; tryCount < generation; tryCount++) {
            List<Direction> rotatedDirections = new ArrayList<>();

            for (int backIndex = curves.size() - 1; backIndex > 0; backIndex--) {
                Point before = curves.get(backIndex);
                Point moreBefore = curves.get(backIndex - 1);

                Direction backward = Direction.find(before, moreBefore);

                rotatedDirections.add(backward.getCwDirection());
            }

            Point endPoint = curves.get(curves.size() - 1);

            for (int index = 0; index < rotatedDirections.size(); index++) {
                Point nextPoint = getNextPoint(endPoint, rotatedDirections.get(index));

                curves.add(nextPoint);

                endPoint = nextPoint;
            }
        }

        return curves;
    }

    private static int getSquarePointCount(boolean[][] isDragonCurve) {
        int count = 0;

        for (int y = 0; y < MAX_SIZE - 1; y++) {
            for (int x = 0; x < MAX_SIZE - 1; x++) {
                if (isDragonCurve[y][x] && isDragonCurve[y + 1][x] && isDragonCurve[y][x + 1] && isDragonCurve[y + 1][x + 1]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void markPoints(boolean[][] map, List<Point> points) {
        for (Point point : points) {
            map[point.getY()][point.getX()] = true;
        }
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


    public enum Direction {
        RIGHT(0, 3),
        LEFT(2, 1),
        BOTTOM(1, 0),
        TOP(3, 2);

        private final int code;
        private final int cwCode;

        Direction(int code, int cwCode) {
            this.code = code;
            this.cwCode = cwCode;
        }

        private final static Map<Integer, Direction> CODE_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(Direction::getCode, Function.identity()));

        public static Direction find(Point start, Point end) {
            if (start.getX() == end.getX()) {
                return (start.getY() > end.getY()) ? BOTTOM : TOP;
            }

            if (start.getY() == end.getY()) {
                return (start.getX() > end.getX()) ? LEFT : RIGHT;
            }

            return null;
        }

        public static Direction of(int code) {
            return CODE_MAP.get(code);
        }

        public int getCode() {
            return this.code;
        }

        public Direction getCwDirection() {
            return Direction.of(this.cwCode);
        }
    }
}
