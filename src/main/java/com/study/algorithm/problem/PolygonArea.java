package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 8. 10..
 */
public class PolygonArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        Point[] points = new Point[count];

        for (int index = 0; index < count; index++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();

            points[index] = new Point(x, y);
        }

        double sum = 0;
        Point before = new Point(points[1].getX() - points[0].getX(), points[1].getY() - points[0].getY());

        for (int index = 2; index < count; index++) {
            Point next = new Point(points[index].getX() - points[0].getX(), points[index].getY() - points[0].getY());
            sum += crossProduct(before, next);
            before = next;
        }

        System.out.printf("%.1f", Math.abs(sum) / 2.0);
    }

    public static double crossProduct(Point a, Point b) {
        return a.getX() * b.getY() - a.getY() * b.getX();
    }

    public static class Point {
        private double x;
        private double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }
    }
}
