package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/29/2019.
 * #1061 https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
 */
public class LexicographicallySmallestEquivalentString {

    // time O(n), space O(1)
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(s1, s2);
        int n = baseStr.length();
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = uf.get(baseStr.charAt(i) - 'a');
        }
        return String.valueOf(arr);
    }

    private static class UnionFind {

        private int[] root;
        private int[] rank;
        private char[] vals;

        public UnionFind(String s1, String s2) {
            int n = s1.length();
            root = new int[26]; // O(n)
            rank = new int[26];
            vals = new char[26];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            for (char x = 'a'; x <= 'z'; x++) {
                vals[x - 'a'] = x;
            }
            for (int i = 0; i < n; i++) { // O(m)
                union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
            }
            for (int i = 0; i < 26; i++) {
                int r = find(i);
                char c = (char) (i + 'a');
                if (c < vals[r]) {
                    vals[r] = c;
                }
            }
        }

        public char get(int x) {
            return vals[find(x)];
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
