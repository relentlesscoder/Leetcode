package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/29/2023.
 * #2085 https://leetcode.com/problems/count-common-words-with-one-occurrence/
 */
public class CountCommonWordsWithOneOccurrence {

    // time O(n + m), space O(n)
    public int countWords(String[] words1, String[] words2) {
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for (String w1 : words1) {
            map.put(w1, map.getOrDefault(w1, 0) + 1);
        }
        for (String w2 : words2) {
            if (map.getOrDefault(w2, 0) <= 1) {
                map.put(w2, map.getOrDefault(w2, 0) - 1);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key) == 0) {
                res++;
            }
        }
        return res;
    }
}
