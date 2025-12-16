package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 10/25/2019.
 * #0990 https://leetcode.com/problems/satisfiability-of-equality-equations/
 */
public class SatisfiabilityOfEqualityEquations {

    // time O(n), space O(1)
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind();
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                uf.union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
            }
        }
        for (String eq : equations) {
            if (eq.charAt(1) == '!'
                    && uf.find(eq.charAt(0) - 'a') == uf.find(eq.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

    private static class UnionFind {
        private int[] root;
        private int[] rank;

        public UnionFind() {
            int n = 26;
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
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
