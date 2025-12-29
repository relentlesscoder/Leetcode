package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 10/25/2019.
 * #1202 https://leetcode.com/problems/smallest-string-with-swaps/
 */
public class SmallestStringWithSwaps {

    // time O(n + m), space O(n)
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] arr = s.toCharArray();
        UnionFind uf = new UnionFind(arr, pairs);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = uf.nextChar(i);
        }
        return String.valueOf(arr);
    }

    private static class UnionFind {

        private final int n;
        private int[] root;
        private int[] rank;
        private Map<Integer, int[]> freq;

        public UnionFind(char[] arr, List<List<Integer>> pairs) {
            n = arr.length;
            root = new int[n]; // O(n)
            rank = new int[n];
            freq = new HashMap<>();
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            for (List<Integer> p : pairs) { // O(m)
                union(p.get(0), p.get(1));
            }
            for (int i = 0; i < n; i++) {
                int r = find(i);
                int[] curr = freq.computeIfAbsent(r, k -> new int[26]);
                curr[arr[i] - 'a']++;
            }
        }

        public char nextChar(int x) {
            char c = '#';
            int[] f = freq.get(find(x));
            for (int i = 0; i < 26; i++) {
                if (f[i] > 0) {
                    f[i]--;
                    c = (char) (i + 'a');
                    break;
                }
            }
            return c;
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
