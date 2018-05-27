package com.study.algorithm.problem;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 5. 27..
 */
public class StringExplosion {
    private static final String NOT_REMAINED = "FRULA";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String searchTarget = scanner.nextLine();

        System.out.println(getRemained(text, searchTarget));
    }


    public static String getRemained(String text, String searchKeyword) {
        char[] remained = new char[text.length()];

        int position = 0;

        int searchTextLength = searchKeyword.length();
        char lastLetter = searchKeyword.charAt(searchTextLength - 1);

        for (int index = 0; index < text.length(); index++) {
            char input = text.charAt(index);
            remained[position++] = input;

            if (input == lastLetter) {
                String substring = new String(remained, Math.max(0, position - searchTextLength), searchTextLength);

                if (substring.equals(searchKeyword)) {
                    position = position - searchTextLength;
                }
            }
        }

        return (position == 0) ? NOT_REMAINED : new String(remained, 0, position);
    }
}
