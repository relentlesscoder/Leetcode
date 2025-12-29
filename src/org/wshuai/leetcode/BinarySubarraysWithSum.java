package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/07/2019.
 * #0930 https://leetcode.com/problems/binary-subarrays-with-sum/
 */
public class BinarySubarraysWithSum {

    // time O(n), space O(n)
    public int numSubarraysWithSumPrefixSum1(int[] nums, int goal) {
        int res = 0, n = nums.length;
        // 数组存前缀和出现的次数
        int[] map = new int[30_001];
        map[0] = 1;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            if (sum - goal >= 0) {
                res += map[sum - goal];
            }
            map[sum]++;
        }
        return res;
    }

    // time O(n), space O(n)
    public int numSubarraysWithSumPrefixSum2(int[] nums, int goal) {
        int res = 0, n = nums.length;
        // 哈希表存前缀和出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            res += map.getOrDefault(sum - goal, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return res;
    }

    // time O(n), space O(1)
    public int numSubarraysWithSum3Pointers(int[] nums, int goal) {
        // 下面滑动窗口解法的3指针实现
        int res = 0, n = nums.length;
        for (int i = 0, j1 = 0, j2 = 0, sum1 = 0, sum2 = 0; i < n; i++) {
            sum1 += nums[i];
            while (j1 <= i && sum1 >= goal) {
                sum1 -= nums[j1++];
            }
            sum2 += nums[i];
            while (j2 <= i && sum2 >= goal + 1) {
                sum2 -= nums[j2++];
            }
            res += j1 - j2;
        }
        return res;
    }

    // time O(n), space O(1)
    public int numSubarraysWithSumSlidingWindow(int[] nums, int goal) {
        // 和为goal的子数组的数量 = 和大于等于goal的子数组的数量 - 和大于等于goal + 1的子数组的数量
        return solve(nums, goal) - solve(nums, goal + 1);
    }

    private int solve(int[] nums, int goal) {
        // 用滑动窗口计算和大于等于goal的子数组的数目
        int res = 0, n = nums.length;
        for (int i = 0, j = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            // 当当前窗口的sum >= goal，收缩左端点直到sum < goal。所有以[0, j - 1]
            // 开始而以i结尾的子数组都是合法的。
            while (j <= i && sum >= goal) {
                sum -= nums[j++];
            }
            // 加上子数组的个数
            res += j;
        }
        return res;
    }
}
