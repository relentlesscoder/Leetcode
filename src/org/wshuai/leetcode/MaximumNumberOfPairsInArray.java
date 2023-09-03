package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #2341 https://leetcode.com/problems/maximum-number-of-pairs-in-array/description/
 */
public class MaximumNumberOfPairsInArray {

    // time O(n), space O(1)
    public int[] numberOfPairs(int[] nums) {
        int[] map = new int[101];
        int remain = 0, pairs = 0;
        for (int num : nums) {
            map[num]++;
        }
        for (int count : map) {
            pairs += count/2;
            remain += (count & 1);
        }
        return new int[]{pairs, remain};
    }
}
