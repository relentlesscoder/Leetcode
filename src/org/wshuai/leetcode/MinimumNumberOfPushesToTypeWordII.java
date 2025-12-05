package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Wei on 01/24/2024.
 * #3016 https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-ii/
 */
public class MinimumNumberOfPushesToTypeWordII {

    // time O(n), space O(1)
    public int minimumPushes(String word) {
        Integer[] map = new Integer[26];
        Arrays.fill(map, 0);
        for (char c : word.toCharArray()) {
            map[c - 'a']++;
        }
        Arrays.sort(map, Collections.reverseOrder());
        int res = 0;
        for (int i = 1, j = 1; i <= 26; i++) {
            res += j * map[i - 1];
            if (i % 8 == 0) {
                j++;
            }
        }
        return res;
    }
}
