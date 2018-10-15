package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 10. 15..
 */
public class FenwickTreeProblem {
    public static class FenwickTree {
        private long[] partitionSum;
        private long[] values;

        public FenwickTree(long[] values) {
            this.values = values;
            this.calculatePartitionSum();
        }

        private void calculatePartitionSum() {
            int size = this.getFloorPower2(values.length) + 1;
            this.partitionSum = new long[size];

            for (int index = 1; index < this.partitionSum.length; index++) {
                long sum = 0;

                for (int position = index; position > index - (index & -index); position--) {
                    if (position < this.values.length) {
                        sum += this.values[position];
                    }
                }

                this.partitionSum[index] = sum;
            }
        }

        private int getFloorPower2(int number) {
            int floor = 1;

            while (number > floor) {
                floor <<= 1;
            }

            return floor;
        }

        public void update(int position, long value) {
            long difference = value - this.values[position];
            int nextPosition = position;

            while (nextPosition < partitionSum.length) {
                partitionSum[nextPosition] += difference;
                nextPosition += (nextPosition & -nextPosition);
            }

            this.values[position] = value;
        }

        public long sum(int from, int to) {
            return this.sum(to) - this.sum(from - 1);
        }

        private long sum(int to) {
            long sum = 0;

            while (to > 0) {
                sum += partitionSum[to];
                to -= (to & -to);
            }

            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt() + 1;
        int modifyCount = scanner.nextInt();
        int testcase = scanner.nextInt();

        long[] values = new long[size];

        //save input data to partitionSum
        for (int index = 1; index < size; index++) {
            values[index] = scanner.nextInt();
        }

        FenwickTree fenwickTree = new FenwickTree(values);

        int testTry = 0;
        long[] answers = new long[testcase];

        for (int index = 0; index < modifyCount + testcase; index++) {
            switch (scanner.nextInt()) {
                case 1:
                    int position = scanner.nextInt();
                    int updateValue = scanner.nextInt();

                    fenwickTree.update(position, updateValue);

                    break;

                case 2:
                    int from = scanner.nextInt();
                    int to = scanner.nextInt();

                    answers[testTry++] = fenwickTree.sum(from, to);

                    break;
            }
        }

        //print answer
        for (int index = 0; index < testcase; index++) {
            System.out.println(answers[index]);
        }
    }


}
