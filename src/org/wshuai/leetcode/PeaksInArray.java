package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2025.
 * #3187 https://leetcode.com/problems/peaks-in-array/
 */
public class PeaksInArray {

    // time O((m + n) * log(n)), space O(n)
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // 统计所有的峰值元素
        int[] peaks = new int[n];
        for (int i = 1; i <= n - 2; i++) { // O(n)
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks[i] = 1;
            }
        }
        BIT bit = new BIT(peaks); // O(n)
        for (int[] q : queries) { // O(m)
            if (q[0] == 1) { // 查询在范围中的峰值
                int left = q[1] + 1, right = q[2] - 1;
                res.add(Math.max(0, bit.query(left + 1, right + 1))); // O(log(n))
            } else {
                int idx = q[1], val = q[2];
                // 先去掉idx - 1，idx和idx + 1在线段树中的峰值数量
                for (int i = Math.max(1, idx - 1); i <= Math.min(n - 2, idx + 1); i++) { // O(log(n))
                    if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                        bit.update(i + 1, -1);
                    }
                }
                nums[idx] = val;
                // 再根据新的值，更新idx - 1，idx和idx + 1在线段树中的峰值数量
                for (int i = Math.max(1, idx - 1); i <= Math.min(n - 2, idx + 1); i++) { // O(log(n))
                    if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                        bit.update(i + 1, 1);
                    }
                }
            }
        }
        return res;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public BIT(int[] nums) {
            int n = nums.length;
            tree = new int[n + 1];
            for (int i = 1; i <= n; i++) { // 线性时间复杂度初始化
                tree[i] += nums[i - 1];
                int idx = i + (i & -i);
                if (idx <= n) {
                    tree[idx] += tree[i];
                }
            }
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += (index & -index);
            }
        }

        private int pre(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= (index & -index);
            }
            return res;
        }

        public int query(int left, int right) {
            return pre(right) - pre(left - 1);
        }
    }
}
