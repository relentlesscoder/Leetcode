package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Wei on 12/14/2025.
 * #3493 https://leetcode.com/problems/properties-graph/
 */
public class PropertiesGraph {

    // time O(n^2 * m), space O(n^2)
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        UnionFind uf = new UnionFind(n);
        HashSet<Integer>[] sets = new HashSet[n];
        Arrays.setAll(sets, i -> new HashSet<>());
        for (int i = 0; i < n; i++) {
            for (int num : properties[i]) {
                sets[i].add(num);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int count = 0;
                for (int num : sets[i]) {
                    if (sets[j].contains(num)) {
                        count++;
                    }
                }
                if (count >= k) {
                    uf.union(i, j);
                }
            }
        }
        return uf.countComponents();
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
            this.count--;
        }

        public int countComponents() {
            return this.count;
        }
    }
}
