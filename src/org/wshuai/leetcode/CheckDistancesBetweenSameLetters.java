package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/29/2023.
 * #2399 https://leetcode.com/problems/check-distances-between-same-letters/
 */
public class CheckDistancesBetweenSameLetters {

    // time O(n), space O(1)
    public boolean checkDistances(String s, int[] distance) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c - 'a'] == -1) {
                map[c - 'a'] = i;
            } else {
                map[c - 'a'] = i - map[c - 'a'] - 1;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] != -1 && map[i] != distance[i]) {
                return false;
            }
        }
        return true;
    }
}
