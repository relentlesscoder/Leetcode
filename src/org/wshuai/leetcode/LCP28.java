package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/03/2025.
 * #LCP28 https://leetcode.cn/problems/4xy4Wx/
 */
public class LCP28 {

    private static final int MOD = (int) 1e9 + 7;

    // time O(n * log(n)), space O(1)
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        long res = 0;
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; ) {
            if (nums[i] + nums[j] <= target) {
                res += j - i;
                i++;
            } else {
                j--;
            }
        }
        return (int) (res % MOD);
    }
}
