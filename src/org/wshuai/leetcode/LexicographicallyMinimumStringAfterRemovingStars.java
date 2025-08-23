package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Created by Wei on 08/23/2025.
 * #3170 https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
 */
public class LexicographicallyMinimumStringAfterRemovingStars {

    // time O(n), space O(n)
    public String clearStars(String s) {
        StringBuilder res = new StringBuilder();
        ArrayDeque<Integer>[] map = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            map[i] = new ArrayDeque<>();
        }
        boolean[] removed = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                removed[i] = true;
                for (int j = 0; j < 26; j++) {
                    if (!map[j].isEmpty()) {
                        removed[map[j].pop()] = true;
                        break;
                    }
                }
            } else {
                map[c - 'a'].push(i);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

    // time O(n * log(n)), space O(n)
    public String clearStarsMinQueue(String s) {
        StringBuilder res = new StringBuilder();
        boolean[] removed = new boolean[s.length()];
        PriorityQueue<int[]> minQueue = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '*') {
                removed[i] = true;
                if (!minQueue.isEmpty()) {
                    removed[minQueue.poll()[1]] = true;
                }
            } else {
                minQueue.offer(new int[]{c - 'a', i});
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!removed[i]) {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}
