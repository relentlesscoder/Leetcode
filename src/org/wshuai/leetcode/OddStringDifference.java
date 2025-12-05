package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/07/2024.
 * #2451 https://leetcode.com/problems/odd-string-difference/
 */
public class OddStringDifference {

    // time O(n * m), space O(m)
    public String oddString(String[] words) {
        int m = words.length, n = words[0].length();
        for (int j = 1; j < n; j++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                int diff = words[i].charAt(j) - words[i].charAt(j - 1);
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }
            int odd = 1_000;
            for (int key : map.keySet()) {
                if (map.get(key) == 1) {
                    odd = key;
                }
            }
            for (int i = 0; i < m; i++) {
                if (words[i].charAt(j) - words[i].charAt(j - 1) == odd) {
                    return words[i];
                }
            }
        }
        return "";
    }
}
