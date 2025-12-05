package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2025.
 * #3158 https://leetcode.com/problems/find-the-xor-of-numbers-which-appear-twice/
 */
public class FindTheXOROfNumbersWhichAppearTwice {

    // time O(n), space O(1)
    public int duplicateNumbersXOR(int[] nums) {
        int res = 0;
        int[] map = new int[51];
        for (int num : nums) {
            if (map[num] == 1) {
                res ^= num;
            }
            map[num]++;
        }
        return res;
    }
}
