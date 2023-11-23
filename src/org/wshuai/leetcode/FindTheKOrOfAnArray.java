package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2917 https://leetcode.com/problems/find-the-k-or-of-an-array/
 */
public class FindTheKOrOfAnArray {

    // time O(n), space O(1)
    public int findKOr(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                count += ((nums[j] >> i) & 1);
            }
            res += (count >= k ? (1 << i) : 0);
        }
        return res;
    }
}
