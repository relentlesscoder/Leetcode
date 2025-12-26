package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/25/2025.
 * #2382 https://leetcode.com/problems/maximum-segment-sum-after-removals/
 */
public class MaximumSegmentSumAfterRemovals {

    // time O(n), space O(n)
    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        // 正难则反，倒叙遍历removeQueries依次把元素加回到并查集中。
        int n = nums.length;
        long[] res = new long[n];
        UnionFind uf = new UnionFind(n);
        for (int i = n - 1; i >= 0; i--) {
            res[i] = uf.getMaxSum();
            uf.add(removeQueries[i], nums[removeQueries[i]]);
        }
        return res;
    }

    private class UnionFind {

        private final int n;
        private long maxSum;
        private int[] root;
        private int[] rank;
        private long[] sum; // 维护连通分量的元素和

        public UnionFind(int n) {
            this.n = n;
            root = new int[n];
            rank = new int[n];
            sum = new long[n];
            maxSum = 0;
            Arrays.fill(root, -1);
        }

        public long getMaxSum() {
            return this.maxSum;
        }

        public void add(int idx, int val) {
            root[idx] = idx;
            rank[idx] = 1;
            sum[idx] = val;
            maxSum = Math.max(maxSum, val);
            // 连通左邻右舍
            if (idx > 0 && root[idx - 1] != -1) {
                union(idx, idx - 1);
            }
            if (idx < n - 1 && root[idx + 1] != -1) {
                union(idx, idx + 1);
            }
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                rank[rootX] += rank[rootY];
                sum[rootX] += sum[rootY];
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
                sum[rootY] += sum[rootX];
            }
            maxSum = Math.max(maxSum, Math.max(sum[rootX], sum[rootY]));
        }
    }
}
