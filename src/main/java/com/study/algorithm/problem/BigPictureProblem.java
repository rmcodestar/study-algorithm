package com.study.algorithm.problem;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;

import java.util.Scanner;

/**
 * Created by rmcodestar on 2018. 5. 27..
 */
public class BigPictureProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int patternY = scanner.nextInt();
        int patternX = scanner.nextInt();
        int otherY = scanner.nextInt();
        int otherX = scanner.nextInt();

        String[] pattern = new String[patternY];
        String[] target = new String[otherY];

        Trie.TrieBuilder trieBuilder = Trie.builder();
        boolean[][] marked = new boolean[otherY][otherX];

        for (int index = 0; index < patternY; index++) {
            pattern[index] = scanner.next();
            trieBuilder.addKeyword(pattern[index]);
        }

        Trie keywordTrie = trieBuilder.build();

        for (int y = 0; y < otherY; y++) {
            target[y] = scanner.next();

            for (Emit emit : keywordTrie.parseText(target[y])) {
                marked[y][emit.getStart()] = true;
            }

        }

        int count = 0;
        for (int y = 0; y <= otherY - patternY; y++) {
            for (int x = 0; x <= otherX - patternX; x++) {
                if (marked[y][x] && pattern[0].equals(target[y].substring(x, x + patternX))) {

                    boolean matched = true;
                    for (int offset = 1; offset < patternY; offset++) {
                        if (!marked[offset + y][x] || !pattern[offset].equals(target[offset + y].substring(x, x + patternX))) {
                            matched = false;
                            break;
                        }
                    }
                    if (matched) {
                        count++;
                    }
                }
            }

        }

        System.out.println(count);
    }
}