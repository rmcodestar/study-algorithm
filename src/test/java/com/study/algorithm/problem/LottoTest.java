package com.study.algorithm.problem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by rmcodestar on 2018. 5. 20..
 */
@Slf4j
public class LottoTest {
    private static final Integer LOTTO_NUM_CNT = 6;

    @Test(timeout = 1000)
    public void test() {
        int[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        LocalDateTime  before = LocalDateTime.now();
        getLotto(items, 0, 0, "");
        LocalDateTime  after = LocalDateTime.now();

        System.out.print(Duration.between(before, after).toMillis());
    }

    /**
     * 3
     * depth == 0
     * 7 - 3
     * @param items
     * @param depth
     * @param position
     * @param lotto
     */
    private void getLotto(int[] items, int depth, int position, String lotto) {
        if(depth == LOTTO_NUM_CNT) {
            System.out.println(lotto);
            return;
        }

        for(int i = position ; i<items.length ; i++) {
            if (items.length - i < LOTTO_NUM_CNT - depth) {
                return;
            }
            getLotto(items, depth + 1, i+1, lotto + items[i] + " ");
        }
    }
}
