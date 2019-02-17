package com.study.algorithm.exam.kakao2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChatting {
    private static final String ENTER = "Enter";
    private static final String LEAVE = "Leave";
    private static final String CHANGE = "Change";

    public static class Log {
        public String userId;
        public String message;

        public Log(String userId, String message) {
            this.userId = userId;
            this.message = message;
        }
    }

    public static String[] solution(String[] inputs) {
        Map<String, String> nickNameMap = new HashMap<>();
        List<Log> logs = new ArrayList<>();

        for (String input : inputs) {
            String[] tokens = input.split(" ");
            String id = tokens[1];

            switch (tokens[0]) {
                case ENTER:
                    nickNameMap.put(id, tokens[2]);
                    logs.add(new Log(id, "님이 들어왔습니다."));
                    break;

                case LEAVE:
                    logs.add(new Log(id, "님이 나갔습니다."));

                    break;
                case CHANGE:
                    nickNameMap.put(id, tokens[2]);
                    break;

            }
        }

        String[] outputs = new String[logs.size()];
        for (int index = 0; index < logs.size(); index++) {
            Log log = logs.get(index);
            outputs[index] = nickNameMap.get(log.userId) + log.message;
        }

        return outputs;
    }

}
