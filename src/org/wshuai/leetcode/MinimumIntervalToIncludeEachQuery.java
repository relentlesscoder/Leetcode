package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/25/2025.
 * #1851 https://leetcode.com/problems/minimum-interval-to-include-each-query/
 */
public class MinimumIntervalToIncludeEachQuery {

    // time O(m * log(m) + n * log(n)), space O(m)
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length, m = queries.length;
        // 按照区间的大小排序
        Arrays.sort(intervals, (a, b) -> a[1] - a[0] - (b[1] - b[0])); // O(n * log(n))
        int[][] qs = new int[m][2];
        Arrays.setAll(qs, i -> new int[]{queries[i], i});
        Arrays.sort(qs, (a, b) -> a[0] - b[0]); // O(m * log(m))
        UnionFind uf = new UnionFind(m + 1);
        int[] res = new int[m];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) { // O(n)
            int left = intervals[i][0], right = intervals[i][1], low = 0, high = m;
            // 二分查找询问中大于等于当前区间左端点的最小索引
            while (low < high) { // O(log(m))
                int mid = low + (high - low) / 2;
                if (qs[mid][0] >= left) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            // 回答所有询问位置在 [left,right] 范围内的还没有被回答过的询问
            for (int j = uf.find(low); j < m && qs[j][0] <= right; j = uf.find(j + 1)) {
                // 把未回答的询问的答案设为当前区间的长度
                res[qs[j][1]] = right - left + 1;
                // 把回答过的询问的根结点设为下一个节点，后续所有查询会跳过这个节点。
                uf.root[j] = j + 1;
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] root;

        public UnionFind(int n) {
            root = new int[n];
            Arrays.setAll(root, i -> i);
        }

        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }
    }
}
