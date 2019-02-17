package com.study.algorithm.exam.kakao2019;

import com.study.algorithm.exam.kakao2019.OpenChatting;
import org.junit.Test;

public class OpenChattingTest {
    @Test
    public void test() {
        String[] inputs = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"};

        for (String output : OpenChatting.solution(inputs)) {
            System.out.println(output);
        }

        //1011점
    }
}