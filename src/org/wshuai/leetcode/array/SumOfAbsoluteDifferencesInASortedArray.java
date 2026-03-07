package org.wshuai.leetcode.array;

/**
 * Created by Wei on 12/21/2020.
 * #1685 https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/
 */
public class SumOfAbsoluteDifferencesInASortedArray {

    // time O(n), space O(1)
    public int[] getSumAbsoluteDifferencesOptimized(int[] nums) {
        // 观察下解发现对每个索引，我们只需要知道当前前缀和和数组元素总和，所以可以进一步
        // 优化去掉前缀和数组而用一个变量存当前前缀和。
        int n = nums.length, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0, s = 0; i < n; i++) {
            int v = nums[i];
            s += v;
            nums[i] = (i + 1) * v - s;
            nums[i] += sum - s - (n - 1 - i) * v;
        }
        return nums;
    }

    // time O(n), space O(n)
    public int[] getSumAbsoluteDifferences(int[] nums) {
        // #2602相似题，因为数组是有序的所以在每个索引 i 前面的数 j 与他的差的绝对值
        // 为 nums[i] - nums[j]。则我们需要求所有这些绝对值的和:
        //  (nums[i] - nums[0]) + (nums[i] - nums[1]) + ... (nums[i] - nums[j])
        //  nums[i] * (j + 1) - (nums[0] + nums[1] + ... + nums[j])
        // 因为 (nums[0] + nums[1] + ... + nums[j]) 即为前缀和 s[i + 1] 所以上式
        // 转化为:
        //  nums[i] * (j + 1) - s[i + 1]
        // 同理 i 右边差绝对值之和为:
        //   (nums[i + 1] - nums[i]) + (nums[i + 2] - nums[i]) + ... (nums[n - 1] - nums[i])
        //   (nums[i + 1] + nums[i + 2] + ... + nums[n - 1]) - nums[i] * (n - 1 - i)
        //   s[n] - s[i + 1] - nums[i] * (n - 1 - i)
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            nums[i] = (i + 1) * v - prefix[i + 1];
            nums[i] += prefix[n] - prefix[i + 1] - (n - 1 - i) * v;
        }
        return nums;
    }
}
