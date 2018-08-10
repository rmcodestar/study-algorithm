package com.study.algorithm.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 8. 10..
 */
public class Ccw {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<>();

        for (int index = 0; index < 3; index++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            points.add(new Point(x, y));
        }

        Point a = new Point(points.get(1).getX() - points.get(0).getX(), points.get(1).getY() - points.get(0).getY());
        Point b = new Point(points.get(2).getX() - points.get(1).getX(), points.get(2).getY() - points.get(1).getY());

        System.out.print(ccw(a, b));
    }

    public static int ccw(Point a, Point b) {
        int ccw = a.getX() * b.getY() - a.getY() * b.getX();

        if (ccw > 0) {
            return 1;
        } else if (ccw < 0) {
            return -1;
        } else {
            return 0;
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
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }
}
