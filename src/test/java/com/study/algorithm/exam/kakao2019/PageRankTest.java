package com.study.algorithm.exam.kakao2019;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * regeexper : https://regexper.com
 * 테스트 1 〉	통과 (39.78ms, 51.2MB)
 * 테스트 2 〉	통과 (55.14ms, 47.3MB)
 * 테스트 3 〉	통과 (36.34ms, 48.8MB)
 * 테스트 4 〉	실패 (37.95ms, 52.8MB)
 * 테스트 5 〉	통과 (36.85ms, 52MB)
 * 테스트 6 〉	실패 (38.19ms, 52.8MB)
 * 테스트 7 〉	통과 (48.00ms, 49.2MB)
 * 테스트 8 〉	실패 (50.68ms, 48.2MB)
 * 테스트 9 〉	통과 (37.95ms, 52.6MB)
 * 테스트 10 〉	실패 (35.81ms, 53MB)
 * 테스트 11 〉	통과 (27.33ms, 48MB)
 * 테스트 12 〉	통과 (26.73ms, 47.8MB)
 * 테스트 13 〉	통과 (27.36ms, 50MB)
 * 테스트 14 〉	통과 (31.87ms, 49.6MB)
 * 테스트 15 〉	통과 (28.10ms, 47.8MB)
 * 테스트 16 〉	통과 (13.88ms, 45.4MB)
 * 테스트 17 〉	실패 (26.21ms, 50.4MB)
 * 테스트 18 〉	통과 (9.35ms, 48.4MB)
 * 테스트 19 〉	통과 (15.47ms, 45.8MB)
 * 테스트 20 〉	통과 (35.23ms, 48.6MB)
 * <p>
 * 75
 */
public class PageRankTest {
    PageRank pageRank = new PageRank();

    @Test
    public void test() {
        String input = "aba@aba aba";

        input = input.replaceAll("[^a-zA-Z]", " ");
        Pattern URL_PATTERN = Pattern.compile(".*\\baba$");
        Matcher matcher = URL_PATTERN.matcher(input.toLowerCase());

        int count = 0;
        if (matcher.find()) {
            count++;
        }

        System.out.println(count);
    }

    @Test
    public void test1() {
        String[] inputs = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>"
                , "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>"
                , "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        };

        assertThat(pageRank.solution("blind", inputs), is(0));
    }


    @Test
    public void test2() {
        String[] inputs = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        };

        assertThat(pageRank.solution("Muzi", inputs), is(1));
    }

}