package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/03/2023.
 * #2158 https://leetcode.com/problems/amount-of-new-area-painted-each-day/
 */
public class AmountOfNewAreaPaintedEachDay {

    // time O(m + n), space O(m + n)
    public int[] amountPaintedUnionFind(int[][] paint) {
        // #1851，#3244相似题
        int n = paint.length, m = 0;
        int[] res = new int[n];
        for (int[] p : paint) { // O(n)
            m = Math.max(m, p[1]);
        }
        UnionFind uf = new UnionFind(m + 1);
        for (int i = 0; i < n; i++) { // total O(n + m)
            int start = paint[i][0], end = paint[i][1];
            // 绘制 [start,end] 范围内的还没有被绘制过的区域
            for (int j = uf.find(start); j < end; j = uf.find(j + 1)) {
                res[i]++;
                // 把绘制过的区域的根结点设为下一个节点，后续所有绘制会跳过这个节点。
                uf.root[j] = j + 1;
            }
        }
        return res;
    }

    private static class UnionFind {

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

    // time O(m + n), space O(m + n)
    public int[] amountPaintedRecordJumpPoint(int[][] paint) {
        // #3244相似题
        int n = paint.length, m = 0;
        int[] res = new int[n];
        for (int[] p : paint) {
            m = Math.max(m, p[1]);
        }
        int[] jump = new int[m];
        for (int i = 0; i < n; i++) {
            int start = paint[i][0], end = paint[i][1];
            for (int j = start; j < end; ) {
                int next = Math.max(j + 1, jump[j]);
                if (jump[j] == 0) {
                    res[i]++;
                    jump[j] = end;
                }
                j = next;
            }
        }
        return res;
    }
}
