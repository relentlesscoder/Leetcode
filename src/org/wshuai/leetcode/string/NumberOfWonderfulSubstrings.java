package org.wshuai.leetcode.string;

/**
 * Created by Wei on 09/22/2023.
 * #1915 https://leetcode.com/problems/number-of-wonderful-substrings/
 */
public class NumberOfWonderfulSubstrings {

    // time O(n), space O(2^10)
    public long wonderfulSubstrings(String word) {
		// 同 #1542
        long res = 0, n = word.length();
        int[] map = new int[1_024];
        map[0] = 1;
        for (int i = 0, mask = 0; i < n; i++) {
            mask ^= 1 << (word.charAt(i) - 'a');
            for (int j = 0; j < 10; j++) {
                res += map[mask ^ (1 << j)];
            }
            res += map[mask];
            map[mask]++;
        }
        return res;
    }
}
