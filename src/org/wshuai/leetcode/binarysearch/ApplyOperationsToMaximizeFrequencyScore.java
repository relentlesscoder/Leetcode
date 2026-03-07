package org.wshuai.leetcode.binarysearch;

import java.util.Arrays;

/**
 * Created by Wei on 12/01/2025.
 * #2968 https://leetcode.com/problems/apply-operations-to-maximize-frequency-score/
 */
public class ApplyOperationsToMaximizeFrequencyScore {

    // time O(n * log(n)), space O(1)
    public int maxFrequencyScoreSlidingWindow(int[] nums, long k) {
        // 对每个滑动窗口边界的改变计算操作数的改变量 (加入或者移除元素) 。
        int res = 0, n = nums.length;
        long operations = 0;
        Arrays.sort(nums);
        for (int i = 0, j = 0; i < n; i++) {
            // 加入元素:
            //    a. 窗口大小是奇数: 之前窗口的中位数 nums[(l + r') / 2] 还是新窗口的中
            //       位数。 因为旧窗口 [l, r'] 的操作数不变，所以加入元素的总增量是新加入
            //       的元素和中位数之差 nums[i] - nums[(l + r) / 2] 。
            //    示例1:
            //    l      r'  r
            //    0   1  2 | 3
            //    2   4  7 | 9
            //        m
            //    b. 窗口大小是偶数: 之前窗口的右中位数 [(l + r' + 1) / 2] 变成新窗口
            //       的中位数, 由下解可知把窗口中元素变为两个中位数的总操作数一样所以旧窗
            //       口 [l, r'] 的操作数保持不变。加入元素的总增量还是新加入的元素和中位
            //       数之差 nums[i] - nums[(l + r) / 2] 。
            //    示例2:
            //    l     r'   r
            //    0 1 2 3 |  4
            //    2 4 7 9 | 11
            //      m m'
            operations += (long) nums[i] - nums[j + (i - j) / 2];
            while (operations > k) {
                // 移除元素: 移除元素和加入元素一样，唯一的区别是中位数是
                //    nums[(l + r + 1) / 2] 。
                //    a. 窗口大小是奇数:
                //    l   l'        r
                //    0 | 1 2    3  4
                //    2 | 4 7    9 11
                //          m
                //    b. 窗口大小是偶数:
                //    l   l'        r
                //    0 | 1 2 3  4  5
                //    2 | 4 7 9 11 13
                //          m m'
                operations -= (long) nums[j + (i - j + 1) / 2] - nums[j];
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int maxFrequencyScoreSlidingWindowPrefixSum(int[] nums, long k) {
        // #1838相似
        int res = 0, n = nums.length;
        Arrays.sort(nums); // 给数组排序
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) { // 计算前缀和
            prefix[i + 1] = prefix[i] + nums[i];
        }
        for (int i = 0, j = 0; i < n; i++) {
            while (countOperations(j, i, nums, prefix) > k) {
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private long countOperations(int left, int right, int[] nums, long[] prefix) {
        // 计算把子数组 [left, right] 全部变成相同的值需要的最小操作。保证最小操作我们应该
        // 选择中位数。两种情况:
        //   1. 子数组长度为奇数 - 直接把所有的元素都变为
        //     nums[left + (right - left) / 2];
        //   2. 子数组长度为偶数 - 我们有两个选择 nums[mid] 和 nums[mid + 1]。我们可以任
        //     选其中一个 (画一条直线并标记数与数之间的距离可以看到总距离到这两个点是一样的)。
        // 我们可以用前缀和数组用 O(1) 的时间复杂度计算出两边的距离。
        int mid = left + (right - left) / 2;
        long target = nums[mid];
        long leftCost = target * (mid - left + 1) - (prefix[mid + 1] - prefix[left]);
        long rightCost = prefix[right + 1] - prefix[mid + 1] - target * (right - mid);
        return leftCost + rightCost;
    }
}
