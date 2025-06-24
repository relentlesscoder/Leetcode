package org.wshuai.leetcode;

/**
 * Created by Wei on 06/23/2025.
 * #3371 https://leetcode.com/problems/identify-the-largest-outlier-in-an-array/
 */
public class IdentifyTheLargestOutlierInAnArray {

    // time O(n), space O(1)
    public int getLargestOutlier(int[] nums) {
        int[] map = new int[2001];
        int res = -1001, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            map[nums[i] + 1000]++;
            sum += nums[i];
        }
        for (int num : nums) {
            map[num + 1000]--;
            if ((sum - num) % 2 == 0) {
                int diff = (sum - num) / 2;
                if (diff >= -1000 && diff <= 1000 && map[diff + 1000] > 0) {
                    res = Math.max(res, num);
                }
            }
            map[num + 1000]++;
        }
        return res;
    }
}
