package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2025.
 * #3511 https://leetcode.com/problems/make-a-positive-array/
 */
public class MakeAPositiveArray {

    // time O(n), space O(1)
    public int makeArrayPositive(int[] nums) {
        // 从索引2开始遍历数组，找到以当前索引为结尾的长度至少为3的子数组和的最小值。
        // 如果这个最小值非正，则增加一个操作把当前数改为1e18 (也可以跳到i + 3)。
        // 如果每个位置的这个最小值都是正的那整个数组就合法了。
        int res = 0, n = nums.length;
        long min = (long) 1e18;
        for (int i = 2; i < n; i++) {
            // 在每个位置i都将min + nums[i]与nums[i] + nums[i - 1] + nums[i - 2]
            // 取最小保证最小子数组至少有三个数。这里min + nums[i] 表示之前最小加上当前
            // 数字。
            long curr = (long) nums[i] + nums[i - 1] + nums[i - 2];
            min = Math.min(min + nums[i], curr);
            if (min <= 0) {
                res++;
                i += 2;
                min = (long) 1e18; // 重置子数组和的最小值
            }
        }
        return res;
    }
}
