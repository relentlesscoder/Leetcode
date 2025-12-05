package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/01/2024.
 * #2506 https://leetcode.com/problems/count-pairs-of-similar-strings/
 */
public class CountPairsOfSimilarStrings {

    // time O(n), space O(n)
    public int similarPairs(String[] words) {
        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }
            int cnt = count.getOrDefault(mask, 0);
            res += cnt;
            count.put(mask, cnt + 1);
        }
        return res;
    }
}
