package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 12/16/2025.
 * #3695 https://leetcode.com/problems/maximize-alternating-sum-using-swaps/
 */
public class MaximizeAlternatingSumUsingSwaps {

    // time O(n * log(n) + m), space O(n)
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        long res = 0;
        int n = nums.length;
        UnionFind uf = new UnionFind(nums, swaps);
        for (int i = 0; i < n; i++) { // O(n)
            if (i % 2 == 0) {
                res += uf.getNextForEven(i); // O(log(n))
            } else {
                res -= uf.getNextForOdd(i);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] root;
        private int[] rank;
        private Map<Integer, TreeMap<Integer, Integer>> maps;

        public UnionFind(int[] nums, int[][] swaps) {
            int n = nums.length;
            root = new int[n];
            rank = new int[n];
            maps = new HashMap<>();
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            for (int[] s : swaps) { // O(m)
                union(s[0], s[1]);
            }
            for (int i = 0; i < n; i++) {
                int r = find(i);
                maps.computeIfAbsent(r, k -> new TreeMap<>()).merge(nums[i], 1, Integer::sum);
            }
        }

        public int getNextForEven(int x) {
            TreeMap<Integer, Integer> curr = maps.get(find(x));
            int max = curr.lastKey();
            int count = curr.merge(max, -1, Integer::sum);
            if (count == 0) {
                curr.remove(max);
            }
            return max;
        }

        public int getNextForOdd(int x) {
            TreeMap<Integer, Integer> curr = maps.get(find(x));
            int min = curr.firstKey();
            int count = curr.merge(min, -1, Integer::sum);
            if (count == 0) {
                curr.remove(min);
            }
            return min;
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
                root[rootY] = rootX;
            } else {
                rank[rootY] += rank[rootX];
                root[rootX] = rootY;
            }
        }
    }
}
