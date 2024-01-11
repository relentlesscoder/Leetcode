package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2606 https://leetcode.com/problems/find-the-substring-with-maximum-cost/
 */
public class FindTheSubstringWithMaximumCost {

    // time O(n), space O(1)
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length();
        int[] map = new int[26];
        for (int i = 1; i <= 26; i++) {
            map[i - 1] = i;
        }
        for (int i = 0; i < chars.length(); i++) {
            map[chars.charAt(i) - 'a'] = vals[i];
        }
        int currSubarray = 0, maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = map[s.charAt(i) - 'a'];
            currSubarray = Math.max(currSubarray + num, num);
            maxSubarray = Math.max(maxSubarray, currSubarray);
        }
        return Math.max(maxSubarray, 0);
    }
}
