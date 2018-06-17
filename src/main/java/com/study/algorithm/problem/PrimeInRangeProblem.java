package com.study.algorithm.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 6. 17..
 */
public class PrimeInRangeProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int from = scanner.nextInt();
        int to = scanner.nextInt();

        printPrime(from, to);
    }

    public static void printPrime(int from, int to) {
        List<Boolean> inputs = new ArrayList<>();

        for(int index = 0; index< 2; index++) {
            inputs.add( false);
        }

        for (int index = 2; index <= to; index++) {
            inputs.add( true);
        }

        for (int index = 2; index <= Math.sqrt(to); index++) {
            if(isPrime(index) && inputs.get(index) == true) {
                for(int j = 2; j * index <= to; j++) {
                    inputs.set(j * index, false);
                }
            }
        }

        for(int index = from; index<=to; index++) {
            if(inputs.get(index)) {
                System.out.println(index);
            }
        }
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
