package org.wshuai.leetcode;

/**
 * Created by Wei on 10/12/2016.
 * #0053 https://leetcode.com/problems/maximum-subarray/
 */
public class MaximumSubarray {

    // time O(n), space O(1)
    public int maxSubArrayOptimizedDP(int[] nums) {
        int res = Integer.MIN_VALUE, maxSum = 0;
        for (int num : nums) {
            maxSum = Math.max(maxSum + num, num);
            res = Math.max(res, maxSum);
        }
        return res;
    }

    // time O(n), space O(n)
    public int maxSubArrayDP(int[] nums) {
        // https://leetcode.cn/problems/maximum-subarray/solutions/9058/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
        int res = Integer.MIN_VALUE, n = nums.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // time O(n), space O(1)
    public int maxSubArrayPrefixSum(int[] nums) {
        // 维护最小前缀和，用当前前缀和减去最小前缀和即为以当前位置结束的子数组
        // 的最大和。由于题目要求子数组不能为空，应当先计算前缀和减取最小前缀和，
        // 再更新最小前缀和。
        int res = Integer.MIN_VALUE, minPreSum = 0, preSum = 0;
        for (int num : nums) {
            preSum += num;
            res = Math.max(res, preSum - minPreSum);
            minPreSum = Math.min(minPreSum, preSum);
        }
        return res;
    }

    // time O(n), space O(log(n))
    public int maxSubArrayDivideAndConquer(int[] nums) {
        int n = nums.length;
        return getMaxSubArray(nums, 0, n - 1).maxSum;
    }

    private record State(int leftSum, int rightSum, int rangeSum, int maxSum) {
    }

    private State getMaxSubArray(int[] nums, int left, int right) {
        if (left == right) {
            return new State(nums[left], nums[left], nums[left], nums[left]);
        }
        int mid = (left + right) / 2;
        State leftState = getMaxSubArray(nums, left, mid);
        State rightState = getMaxSubArray(nums, mid + 1, right);
        return mergeState(leftState, rightState);
    }

    private State mergeState(State leftState, State rightState) {
        int rangeSum = leftState.rangeSum + rightState.rangeSum;
        int leftSum = Math.max(leftState.leftSum, leftState.rangeSum + rightState.leftSum);
        int rightSum = Math.max(rightState.rightSum, rightState.rangeSum + leftState.rightSum);
        int maxSum = Math.max(leftState.maxSum, Math.max(rightState.maxSum, leftState.rightSum + rightState.leftSum));
        return new State(leftSum, rightSum, rangeSum, maxSum);
    }
}
