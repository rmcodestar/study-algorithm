package com.study.algorithm.exam.kakao2019;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PageRank {
    private static final Pattern URL_PATTERN = Pattern.compile("<meta property=\"og:url\" content=\"(.+?)\"/>");
    private static final Pattern OUTER_LINK_PATTERN = Pattern.compile("<a href=\"(.+?)\">");
    private static final String SPACE = " ";

    public class Page {
        public Page(int index) {
            this.index = index;
            this.linkPoint = 0;
            this.basePoint = 0;
            this.links = new ArrayList<>();
        }

        public int index;
        public String url;
        public int basePoint;
        public List<String> links;
        public double linkPoint;

        public void setUrl(String url) {
            this.url = url;
        }

        public double getMatchPoint() {
            return this.linkPoint + this.basePoint;
        }

        public int getIndex() {
            return this.index;
        }

        public void addLink(String link) {
            this.links.add(link);
        }

        public String getUrl() {
            return url;
        }

        public void increaseBasePoint() {
            this.basePoint++;
        }

        public double getPoint() {
            return (this.links.size() > 0) ? this.basePoint / (1.0 * this.links.size()) : 0;
        }

        public void addLinkPoint(double amount) {
            this.linkPoint += amount;
        }
    }


    public int solution(String word, String[] inputs) {
        List<Page> pages = makePages(word, inputs);

        calculateLinkPoint(pages);

        pages.sort(Comparator.comparing(Page::getMatchPoint).reversed()
                .thenComparing(Page::getIndex));

        return pages.get(0).getIndex();
    }

    private List<Page> makePages(String word, String[] htmls) {
        List<Page> pages = new ArrayList<>();

        for (int index = 0; index < htmls.length; index++) {
            String html = htmls[index].toLowerCase();

            Matcher urlMatcher = URL_PATTERN.matcher(html);

            Page page = new Page(index);

            if (urlMatcher.find()) {
                page.setUrl(urlMatcher.group(1));
            }

            Matcher linkMatcher = OUTER_LINK_PATTERN.matcher(html);

            while (linkMatcher.find()) {
                page.addLink(linkMatcher.group(1));
            }

            String content = html.replaceAll("[^a-zA-Z]", SPACE);
            String[] tokens = content.split(SPACE);

            for (String token : tokens) {
                if (token.equals(word.toLowerCase())) {
                    page.increaseBasePoint();
                }
            }

            pages.add(page);
        }

        return pages;
    }

    private void calculateLinkPoint(List<Page> pages) {
        Map<String, Page> pageMap = pages.stream()
                .collect(Collectors.toMap(Page::getUrl, Function.identity()));

        for (Page page : pages) {
            for (String outerLink : page.links) {
                Optional<Page> otherPage = Optional.ofNullable(pageMap.get(outerLink));

                if (otherPage.isPresent()) {
                    otherPage.get().addLinkPoint(page.getPoint());
                }
            }
        }
    }
}
