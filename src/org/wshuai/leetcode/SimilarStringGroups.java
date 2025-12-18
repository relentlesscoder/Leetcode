package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/15/2019.
 * #0839 https://leetcode.com/problems/similar-string-groups/
 */
public class SimilarStringGroups {

    // time O(n^2 * L), space O(n)
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.countComponents();
    }

    private boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (count == 2 && s1.charAt(i) != s2.charAt(i)) {
                return false;
            } else if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count == 2 || count == 0;
    }

    private static class UnionFind {

        private int count;
        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
            this.count = n;
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
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
            this.count--;
        }

        private int countComponents() {
            return this.count;
        }
    }
}
