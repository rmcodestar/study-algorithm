package com.study.algorithm.exam.kakao2019;

import java.util.*;

public class FailureRate {
    public class Stage {
        public int stageNumber;
        public int clearNumbers;
        public int failureNumbers;
        public double failureRate;

        public Stage(int stageNumber) {
            this.stageNumber = stageNumber;
        }

        public void fail() {
            this.failureNumbers++;
        }

        public void clear() {
            this.clearNumbers++;
        }
    }

    public int[] solution(int N, int[] maxStages) {
        List<Stage> stages = new ArrayList<>();

        for (int index = 0; index <= N; index++) {
            stages.add(new Stage(index));
        }

        for (Integer thisTry : maxStages) {
            if (thisTry <= N) {
                stages.get(thisTry).fail();
            }

            for (int clearStage = 1; clearStage <= thisTry; clearStage++) {
                if (clearStage <= N) {
                    stages.get(clearStage).clear();
                }
            }
        }


        stages.remove(0);

        int[] answer = new int[N];

        for (Stage stage : stages) {
            if (stage.clearNumbers > 0) {
                stage.failureRate = (1.0 * stage.failureNumbers) / stage.clearNumbers;
            }
        }

        stages.sort(((o1, o2) -> {
            int result = Double.compare(o2.failureRate, o1.failureRate);

            if (result == 0) {
                return Integer.compare(o1.stageNumber, o2.stageNumber);
            }

            return result;
        }));

        for (int order = 0; order < N; order++) {
            answer[order] = stages.get(order).stageNumber;
        }

        return answer;
    }
}
