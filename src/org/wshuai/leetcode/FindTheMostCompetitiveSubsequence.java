package org.wshuai.leetcode;

/**
 * Created by Wei on 05/16/2021.
 * #1673 https://leetcode.com/problems/find-the-most-competitive-subsequence/
 */
public class FindTheMostCompetitiveSubsequence {

    // time O(n), space O(k)
    public int[] mostCompetitive(int[] nums, int k) {
        // https://leetcode.cn/problems/find-the-most-competitive-subsequence/solutions/2788312/gen-zhao-wo-guo-yi-bian-shi-li-2ni-jiu-m-36c4/
        int n = nums.length;
        // Use array as Monotonic stack
        int[] stack = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            // n + j - i > k ensure there is still enough digits in [i, n - 1]
            // to get a k-length string
            //   n - i - 1 >= k - j
            //   n - i + j - 1 >= k
            //   n - i + j > k
            while (j > 0 && stack[j - 1] > nums[i] && n + j - i > k) {
                j--;
            }
            if (j < k) {
                stack[j++] = nums[i];
            }
        }
        return stack;
    }
}
