package com.study.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmcodestar on 2018. 4. 15..
 */
public class MergeSort {

    public List<Integer> sort(List<Integer> items) {
        if (items.size() == 1) {
            return items;
        }

        int middleIndex = (int) Math.ceil(items.size() / 2.0);

        List<Integer> leftList = sort(items.subList(0, middleIndex));
        List<Integer> rightList = sort(items.subList(middleIndex, items.size()));

        return merge(leftList, rightList);
    }


    private List<Integer> merge(List<Integer> leftList, List<Integer> rightList) {
        int leftIndex = 0;
        int rightIndex = 0;
        List<Integer> sorted = new ArrayList<>();

        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (leftList.get(leftIndex) < rightList.get(rightIndex)) {
                sorted.add(leftList.get(leftIndex));
                leftIndex++;

            } else {
                sorted.add(rightList.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < leftList.size()) {
            sorted.add(leftList.get(leftIndex++));
        }

        while (rightIndex < rightList.size()) {
            sorted.add(rightList.get(rightIndex++));
        }

        return sorted;
    }
}
