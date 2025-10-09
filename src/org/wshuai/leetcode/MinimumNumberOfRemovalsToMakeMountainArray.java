package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/09/2025.
 * #1671 https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 */
public class MinimumNumberOfRemovalsToMakeMountainArray {

    // time O(n * log(n)), space O(n)
    public int minimumMountainRemovalsOptimized(int[] nums) {
        int n = nums.length, res = n;
        int[] tails = new int[n], post = new int[n];
        for (int i = n - 1, len = 0; i >= 0; i--) {
            int num = nums[i];
            int pos = bisect(num, tails, len);
            post[i] = pos + 1;
            tails[pos] = num;
            if (pos == len) {
                len++;
            }
        }
        Arrays.fill(tails, 0);
        for (int i = 0, len = 0; i < n - 1; i++) {
            int num = nums[i];
            int pos = bisect(num, tails, len);
            int lis = pos + 1;
            tails[pos] = num;
            if (pos == len) {
                len++;
            }
            if (lis < 2 || post[i] < 2) {
                continue;
            }
            res = Math.min(res, n - (lis + post[i] - 1));
        }
        return res;
    }

    // time O(n * log(n)), space O(n)
    public int minimumMountainRemovals(int[] nums) {
        // https://leetcode.cn/problems/minimum-number-of-removals-to-make-mountain-array/solutions/2575527/qian-hou-zhui-fen-jie-zui-chang-di-zeng-9vowl/
        int n = nums.length, res = n;
        int[] tails = new int[n], pre = new int[n], post = new int[n];
        for (int i = 0, len = 0; i < n; i++) {
            int num = nums[i];
            int pos = bisect(num, tails, len);
            pre[i] = pos + 1;
            tails[pos] = num;
            if (pos == len) {
                len++;
            }
        }
        Arrays.fill(tails, 0);
        for (int i = n - 1, len = 0; i >= 0; i--) {
            int num = nums[i];
            int pos = bisect(num, tails, len);
            post[i] = pos + 1;
            tails[pos] = num;
            if (pos == len) {
                len++;
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (pre[i] < 2 || post[i] < 2) {
                continue;
            }
            res = Math.min(res, n - (pre[i] + post[i] - 1));
        }
        return res;
    }

    private int bisect(int num, int[] tails, int high) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (tails[mid] < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
