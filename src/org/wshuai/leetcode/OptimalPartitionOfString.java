package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2023.
 * #2405 https://leetcode.com/problems/optimal-partition-of-string/description/
 */
public class OptimalPartitionOfString {

    // time O(n), space O(1)
    public int partitionString(String s) {
        int res = 1, currentStart = 0;
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);
        for (int i = 0; i < s.length(); i++) {
            if (lastIndex[s.charAt(i) - 'a'] >= currentStart) {
                res++;
                currentStart = i;
            }
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        return res;
    }

    // time O(n), space O(1)
    public int partitionStringBitmap(String s) {
        int res = 1, mask = 0;
        for (char current : s.toCharArray()) {
            int shift = current - 'a';
            if ((mask & (1 << shift)) > 0) {
                res++;
                mask = 0;
            }
            mask |= (1 << shift);
        }
        return res;
    }
}
