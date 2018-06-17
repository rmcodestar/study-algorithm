package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 6. 17..
 */
public class PrimeNumberProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalCount = scanner.nextInt();
        int[] numbers = new int[totalCount];

        for (int index = 0; index < numbers.length; index++) {
            numbers[index] = scanner.nextInt();
        }

        System.out.println(findPrimeCount(numbers));
    }

    public static int findPrimeCount(int[] numbers) {
        int primeTotalCount = 0;

        for (Integer number : numbers) {
            if (isPrime(number)) {
                primeTotalCount++;
            }
        }

        return primeTotalCount;
    }

    public static boolean isPrime(int number) {
        if (number <= 2) {
            return (number % 2 == 0);

        } else if (number % 2 == 0) {
            return false;
        }

        for (int index = 3; index <= (int) Math.sqrt(number); index++) {
            if (number % index == 0) {
                return false;
            }
        }

        return true;
    }
}
