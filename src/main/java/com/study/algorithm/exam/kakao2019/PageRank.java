package com.study.algorithm.exam.kakao2019;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageRank {
    private static final Pattern URL_PATTERN = Pattern.compile("<meta property=\"og:url\" content=\"(\\w.*)\"/>");
    private static final Pattern OUTER_LINK_PATTERN = Pattern.compile("<a href=\"(\\w.*)\">");

    public class Page {
        public int index;
        public String url;

        public int base;
        public List<String> links = new ArrayList<>();

        public double linkPoint;

        public double getMatchPoint() {
            return this.linkPoint + base;
        }

        public void addLink(String link) {
            this.links.add(link);
        }

        public Page(int index) {
            this.index = index;
        }
    }


    public int solution(String word, String[] inputs) {
        Map<String, Page> pageMap = new HashMap<>();
        List<Page> pages = new ArrayList<>();
        for (int index = 0; index < inputs.length; index++) {
            String input = inputs[index].toLowerCase();

            Page page = new Page(index);
            Matcher matcher = URL_PATTERN.matcher(input);

            if (matcher.find()) {
                page.url = matcher.group(1);
            }

            matcher = OUTER_LINK_PATTERN.matcher(input);
            while (matcher.find()) {
                page.addLink(matcher.group(1));
            }

            String content = input.replaceAll("[^a-zA-Z]", " ");
            String[] tokens = content.split(" ");

            for (String token : tokens) {
                String lowerWord = word.toLowerCase();
                if (lowerWord.equals(token)) {
                    page.base++;
                }
            }

            pages.add(page);
            pageMap.put(page.url, page);
        }


        for (Page page : pages) {
            double point = (page.links.size() > 0) ? page.base / (1.0 * page.links.size()) : 0;
            for (String outerLink : page.links) {
                Page otherPage = pageMap.get(outerLink);

                if (Objects.nonNull(otherPage)) {
                    otherPage.linkPoint += point;
                }
            }
        }

        pages.sort((p1, p2) -> {
            int result = Double.compare(p2.getMatchPoint(), p1.getMatchPoint());

            if (result != 0) {
                return result;
            }

            return Integer.compare(p1.index, p2.index);
        });

        return pages.get(0).index;
    }
}
