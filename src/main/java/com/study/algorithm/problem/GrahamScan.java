package com.study.algorithm.problem;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Created by rmcodestar on 2018. 11. 12..
 */
@Slf4j
public class GrahamScan {
    public static void main(String[] agrs) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<>();

        int count = scanner.nextInt();

        for (int index = 0; index < count; index++) {
            points.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }

        points.sort((one, two) -> {
            if (one.y < two.y) {
                return -1;
            } else if (one.y > two.y) {
                return 1;
            } else {
                return (one.x < two.x) ? -1 : 1;
            }
        });

        Point basePoint = points.get(0);
        points.remove(0);

        for (Point point : points) {
            point.angle = getAngle(basePoint, point);
            point.distance = getDistance(basePoint, point);
        }

        points.sort((one, two) -> {
            if (one.angle < two.angle) {
                return -1;
            } else if (one.angle > two.angle) {
                return 1;
            } else {
                if (one.distance > two.distance) {
                    return 1;
                } else if (one.distance < two.distance) {
                    return -1;
                }

                return 0;
            }
        });

        points.add(basePoint);

        Stack<Point> stack = new Stack<>();
        stack.push(basePoint);
        stack.push(points.get(0));

        for (int index = 1; index < points.size(); index++) {
            Point p1 = stack.get(stack.size() - 2);
            Point p2 = stack.get(stack.size() - 1);
            Point p3 = points.get(index);

            int ccw = calculateCcw(p1, p2, p3);

            log.info("[p1] {}, [p2] {}, [p3], {}, calculateCcw : {}", p1, p2, p3, ccw);

            if (ccw == 1) {
                stack.push(p3);

            } else if (ccw == -1) {
                stack.pop();

                if (stack.size() >= 2) {
                    index--;
                    continue;
                }

            } else {
                stack.pop();
                stack.push(p3);
            }
        }

        System.out.println(Math.max(stack.size() - 1, 2));
    }

    public static int calculateCcw(Point a, Point b, Point c) {
        long temp1 = a.x * b.y;
        temp1 += b.x * c.y;
        temp1 += c.x * a.y;

        long temp2 = a.y * b.x;
        temp2 += b.y * c.x;
        temp2 += c.y * a.x;

        long ccw = temp1 - temp2;

        if (ccw > 0) {
            return 1;
        }

        return (ccw < 0) ? -1 : 0;
    }

    public static double getAngle(Point one, Point two) {
        long temp1 = two.y - one.y;
        long temp2 = two.x - one.x;

        double rad = Math.atan2(temp1, temp2);
        return (rad * 180) / Math.PI;
    }

    public static long getDistance(Point one, Point two) {
        long distance1 = (long) (two.x - one.x) * (two.x - one.x);
        long distance2 = (long) (two.y - one.y) * (two.y - one.y);

        return Math.abs(distance1 + distance2);
    }

    public static class Point {
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int x;
        public int y;

        public double angle;
        public long distance;

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}

