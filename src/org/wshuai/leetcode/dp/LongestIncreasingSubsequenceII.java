package org.wshuai.leetcode.dp;

/**
 * Created by Wei on 11/12/2025.
 * #2407 https://leetcode.com/problems/longest-increasing-subsequence-ii/
 */
public class LongestIncreasingSubsequenceII {

    // time O(n * log(m)), space O(m)
    public int lengthOfLIS(int[] nums, int k) {
        // 核心思路：LIS 变体，要求相邻元素差 <= k
        // 普通 LIS 的 dp[v] = 以值 v 结尾的最长递增子序列长度
        // 转移：dp[v] = 1 + max(dp[v-k], dp[v-k+1], ..., dp[v-1])
        // 需要快速查询区间 [v-k, v-1] 的最大值 → 用线段树优化到 O(log m)
        int n = nums.length, m = 0;
        // 找到最大值，作为线段树的值域范围
        for (int x : nums) {
            m = Math.max(m, x);
        }
        // 线段树维护：tree[v] = 以值 v 结尾的最长递增子序列长度
        SegmentTree st = new SegmentTree(m + 1);
        for (int i = 0; i < n; i++) {
            // 查询值域 [nums[i]-k, nums[i]-1] 中的最大 LIS 长度
            // Math.max(..., 0) 防止下标为负
            // +1 表示加上 nums[i] 自身
            st.update(nums[i], 1 + st.query(Math.max(nums[i] - k, 0), Math.max(nums[i] - 1, 0)));
        }
        // 线段树根节点存的就是全局最大值
        return st.max();
    }

    // 线段树：维护区间最大值，支持单点更新 + 区间查询
    private static class SegmentTree {

        private final int n;
        private final int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            // 线段树大小：2 倍向上取整到 2 的幂
            this.tree = new int[2 << (32 - Integer.numberOfLeadingZeros(n))];
        }

        // 全局最大值（根节点）
        public int max() {
            return tree[1];
        }

        // 单点更新：将 index 位置的值设为 val
        public void update(int index, int val) {
            update(1, 0, n - 1, index, val);
        }

        // 区间查询：查询 [start, end] 范围内的最大值
        public int query(int start, int end) {
            return query(1, 0, n - 1, start, end);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (left == right) {
                tree[node] = val; // 叶子节点，直接赋值
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(node * 2, left, mid, index, val); // 更新左子树
            } else {
                update(node * 2 + 1, mid + 1, right, index, val); // 更新右子树
            }
            maintain(node); // 回溯时更新父节点
        }

        private int query(int node, int left, int right, int start, int end) {
            if (left >= start && right <= end) {
                return tree[node]; // 当前区间完全包含在查询范围内
            }
            int mid = left + (right - left) / 2;
            if (end <= mid) {
                return query(node * 2, left, mid, start, end); // 查询范围全在左半
            }
            if (start > mid) {
                return query(node * 2 + 1, mid + 1, right, start, end); // 查询范围全在右半
            }
            // 查询范围跨越左右两半，合并结果
            int leftRes = query(node * 2, left, mid, start, end);
            int rightRes = query(node * 2 + 1, mid + 1, right, start, end);
            return merge(leftRes, rightRes);
        }

        // 用左右子节点的值更新父节点
        private void maintain(int node) {
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }

        // 合并操作：取最大值
        private int merge(int v1, int v2) {
            return Math.max(v1, v2);
        }
    }
}
