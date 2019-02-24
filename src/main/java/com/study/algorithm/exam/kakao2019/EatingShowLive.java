package com.study.algorithm.exam.kakao2019;

import java.util.Objects;

public class EatingShowLive {

    public int solution(int[] foodTimes, long afterSeconds) {
        Integer selected = -1;

        for (long second = 0; second <= afterSeconds; second++) {
            selected = getNextFoodIndex(foodTimes, selected.intValue());

            if (Objects.isNull(selected)) {
                return -1;
            }

            foodTimes[selected] = Math.max(foodTimes[selected] - 1, 0);
        }

        return selected + 1;
    }

    private Integer getNextFoodIndex(int[] arr, int current) {
        int count = arr.length;

        if (count == 1) {
            return (arr[0] == 0) ? null : 0;
        }

        for (int index = (current + 1) % count; index <= current + count; index++) {
            if (arr[index % count] != 0) {
                return index % count;
            }

        }

        return null;
    }


}
