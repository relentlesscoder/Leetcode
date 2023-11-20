package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/19/2023.
 * #2744 https://leetcode.com/problems/find-maximum-number-of-string-pairs/
 */
public class FindMaximumNumberOfStringPairs {

    // time O(n * m), space O(n)
    public int maximumNumberOfStringPairs(String[] words) {
        int res = 0;
        Map<String, Integer> needed = new HashMap<>();
        for (String word : words) {
            if (needed.containsKey(word)) {
                res++;
                int count = needed.get(word) - 1;
                if (count > 0) {
                    needed.put(word, count);
                } else {
                    needed.remove(word);
                }
            } else {
                String reverse = new StringBuilder(word).reverse().toString();
                needed.put(reverse, needed.getOrDefault(reverse, 0) + 1);
            }
        }
        return res;
    }
}
