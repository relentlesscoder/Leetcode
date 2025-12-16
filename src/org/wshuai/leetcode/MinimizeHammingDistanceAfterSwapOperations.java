package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 01/14/2021.
 * #1722 https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
 */
public class MinimizeHammingDistanceAfterSwapOperations {

    // time O(m + n), space O(n)
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int res = 0, n = source.length;
        UnionFind uf = new UnionFind(source, allowedSwaps);
        for (int i = 0; i < n; i++) { // O(n)
            Map<Integer, Integer> map = uf.getMap(i);
            if (map.containsKey(target[i])) {
                int count = map.merge(target[i], -1, Integer::sum);
                if (count == 0) {
                    map.remove(target[i]);
                }
            } else {
                res++;
            }
        }
        return res;
    }

    private static class UnionFind {

        private final int n;
        private int[] root;
        private int[] rank;
        private Map<Integer, Map<Integer, Integer>> maps;

        public UnionFind(int[] source, int[][] swaps) {
            n = source.length;
            root = new int[n];
            rank = new int[n];
            maps = new HashMap<>();
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            for (int[] s : swaps) { // O(m)
                union(s[0], s[1]);
            }
            for (int i = 0; i < n; i++) { // O(n)
                int r = find(i);
                Map<Integer, Integer> curr = maps.computeIfAbsent(r, k -> new HashMap<>());
                curr.merge(source[i], 1, Integer::sum);
            }
        }

        private Map<Integer, Integer> getMap(int x) {
            return maps.get(find(x));
        }

        private int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            return root[x];
        }

        private void union(int x, int y) {
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
