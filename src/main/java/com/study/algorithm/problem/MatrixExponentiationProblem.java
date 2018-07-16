package com.study.algorithm.problem;

import java.util.*;

/**
 * Created by rmcodestar on 2018. 7. 15..
 */
public class MatrixExponentiationProblem {
    private static final int DIVISOR = 1000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        long exponent = scanner.nextLong();

        Long[][] input = new Long[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                input[y][x] = scanner.nextLong();
            }
        }

        Long[][] result = calculateMatrix(input, exponent, size);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(result[y][x] % DIVISOR);
                System.out.print((x == size - 1) ? System.lineSeparator() : " ");
            }
        }

    }

    public static Long[][] calculateMatrix(Long[][] input, long exponent, int size) {
        Map<Long, Long[][]> calculated = new HashMap<>();
        calculated.put(1L, input);

        long current;
        for (current = 1; current * 2 < exponent; current *= 2) {
            Long[][] currentMatrix = calculated.get(current);
            calculated.put(current * 2, multiple(currentMatrix, currentMatrix, size));
        }

        while (exponent > current) {
            long before = getMaxKey(exponent - current, calculated.keySet());
            long next = before + current;

            calculated.put(next, multiple(calculated.get(before), calculated.get(current), size));

            current = next;
        }

        return calculated.get(exponent);
    }

    private static long getMaxKey(long max, Set<Long> keyset) {
        List<Long> keys = new ArrayList<>(keyset);

        Collections.sort(keys);

        for (int index = keys.size() - 1; index >= 0; index--) {
            long key = keys.get(index);
            if (key <= max) {
                return key;
            }
        }

        throw new IllegalStateException("do not calculated");
    }

    private static Long[][] multiple(Long[][] target, Long[][] other, int size) {
        Long[][] result = new Long[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                result[y][x] = 0L;

                for (int index = 0; index < size; index++) {
                    result[y][x] += target[y][index] * other[index][x];
                }

                result[y][x] %= DIVISOR;
            }
        }

        return result;
    }
}
