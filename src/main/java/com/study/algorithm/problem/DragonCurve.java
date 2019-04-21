package com.study.algorithm.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DragonCurve {
    private static final int MAX_SIZE = 101;

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean[][] isDragonCurve = new boolean[MAX_SIZE][MAX_SIZE];

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

            findDragonCurve(points, generation);
            markPoints(isDragonCurve, points);
        }

        int count = 0;
        for (int y = 0; y < MAX_SIZE - 1; y++) {
            for (int x = 0; x < MAX_SIZE - 1; x++) {
                if (isDragonCurve[y][x] && isDragonCurve[y + 1][x] && isDragonCurve[y][x + 1] && isDragonCurve[y + 1][x + 1]) {
                    count++;
                }
            }
        }

        System.out.println(count);
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


    private static void findDragonCurve(List<Point> points, int generation) {
        for (int tryCount = 0; tryCount < generation; tryCount++) {

            List<Direction> willDirections = new ArrayList<>();

            for (int backIndex = points.size() - 1; backIndex > 0; backIndex--) {
                Point p1 = points.get(backIndex);
                Point p2 = points.get(backIndex - 1);

                Direction direction = Direction.find(p1.x, p1.y, p2.x, p2.y);

                willDirections.add(direction.getCwDirection());
            }

            Point endPoint = points.get(points.size() - 1);

            for (int index = 0; index < willDirections.size(); index++) {
                Point nextPoint = getNextPoint(endPoint, willDirections.get(index));

                points.add(nextPoint);

                endPoint = nextPoint;
            }
        }
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

        public static Direction find(int x1, int y1, int x2, int y2) {
            if (x1 == x2) {
                return (y1 > y2) ? BOTTOM : TOP;
            }

            if (y1 == y2) {
                return (x1 > x2) ? LEFT : RIGHT;
            }

            return null;
        }

        public static Direction of(int code) {
            for (Direction direction : values()) {
                if (direction.getCode() == code) {
                    return direction;
                }
            }

            return null;
        }

        public int getCode() {
            return this.code;
        }

        public Direction getCwDirection() {
            return Direction.of(this.cwCode);
        }
    }
}
