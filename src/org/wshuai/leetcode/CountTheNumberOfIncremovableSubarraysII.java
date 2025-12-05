package org.wshuai.leetcode;

/**
 * Created by Wei on 12/05/2025.
 * #2972 https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-ii/
 */
public class CountTheNumberOfIncremovableSubarraysII {

    // time O(n), space O(1)
    public long incremovableSubarrayCount(int[] nums) {
        // #1574的变形题
        // 正难则反 - 可以考虑删除递增字数组后留下来的序列有哪些。通过观察
        // 可以发现留下来的序列是所有前缀+后缀拼接成的严格递增序列的合集。
        // 而这些序列的总数就是题目要求的结果。
        // 以下是几个例子：
        // 示例1:
        //   0 1 2 3
        //   6 5 7 8
        //   prefix: []
        //   suffix: [5,7,8] [7,8] [8] []
        //   prefix: [6]
        //   suffix: [7,8] [8] []
        // 示例2:
        //   0 1 2 3 4 5
        //   2 8 7 6 6 9
        //   prefix: []
        //   suffix: [6,9] [9] []
        //   prefix: [2]
        //   suffix: [6,9] [9] []
        //   prefix: [2,8]
        //   suffix: [9] []
        // 示例3:
        //   0 1 2 3
        //   8 7 6 6
        //   prefix: []
        //   suffix: [6] []
        //   prefix: [8]
        //   suffix: []
        long res = 0;
        int n = nums.length, right = n - 1;
        while (right > 0 && nums[right - 1] < nums[right]) {
            right--;
        }
        // 注意唯一特殊的情况是当数组已经是严格递增，这种情况所有删除至少
        // 一个数字的前缀+后缀拼接成 的序列都是合法的。这些序列的总数是
        // n * (n + 1) / 2。
        // 示例4:
        //   0 1 2 3
        //   1 2 3 4
        // prefix: []
        // suffix: [2,3,4] [3,4] [4] []
        // prefix: [1]
        // suffix: [3,4] [4] []
        // prefix: [1,2]
        // suffix: [4] []
        // prefix: [1,2,3]
        // suffix: []
        if (right == 0) {
            return (long) n * (n + 1) / 2;
        }
        res += n - right + 1;
        for (int left = 0; left == 0 || nums[left] > nums[left - 1]; left++) {
            while (right < n && nums[left] >= nums[right]) {
                right++;
            }
            res += n - right + 1;
        }
        return res;
    }
}
