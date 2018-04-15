package com.study.algorithm.sort;

import java.util.Collections;
import java.util.List;

/**
 * Created by rmcodestar on 2018. 4. 15..
 */
public class QuickSort {

    public void sort(List<Integer> items) {
        doQuickSort(items, 0, items.size() - 1);
    }

    private void doQuickSort(List<Integer> items, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int pivotIndex = getPivotIndex(items, leftIndex, rightIndex);

        doQuickSort(items, leftIndex, pivotIndex - 1);
        doQuickSort(items, pivotIndex + 1, rightIndex);
    }

    private int getPivotIndex(List<Integer> items, int leftIndex, int rightIndex) {
        int low = leftIndex;
        int high = rightIndex;
        int pivotIndex = low;
        Integer pivot = items.get(pivotIndex);

        while (low < high) {
            while (items.get(low) <= pivot && low < rightIndex) {
                low++;
            }

            while (items.get(high) > pivot && high > leftIndex) {
                high--;
            }

            if (low < high) {
                Collections.swap(items, low, high);
            }
        }

        Collections.swap(items, pivotIndex, high);

        return high;
    }
}
