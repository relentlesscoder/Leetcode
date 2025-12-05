package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2025.
 * #3046 https://leetcode.com/problems/split-the-array/
 */
public class SplitTheArray {

    // time O(n), space O(1)
    public boolean isPossibleToSplit(int[] nums) {
        int[] freq = new int[101];
        for (int num : nums) {
            if(++freq[num] > 2) {
                return false;
            }
        }
        return true;
    }
}
