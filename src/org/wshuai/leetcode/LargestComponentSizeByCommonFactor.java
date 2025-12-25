package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 11/18/2019.
 * #0952 https://leetcode.com/problems/largest-component-size-by-common-factor/
 */
public class LargestComponentSizeByCommonFactor {

    // time O(n * sqrt(MAX) * log(MAX)), space O(n)
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        // #2521 计算每个元素的不同质因数
        for (int i = 0; i < n; i++) { // O(n)
            int num = nums[i];
            for (int j = 2; j * j <= num; j++) { // O(sqrt(MAX))
                if (num % j == 0) {
                    map.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                    while (num % j == 0) { // O(log(MAX))
                        num /= j;
                    }
                }
            }
            if (num > 1) {
                map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
            }
        }
        // 使用连通集来连通数组中所有共享质因数的元素
        UnionFind uf = new UnionFind(n);
        for (List<Integer> list : map.values()) { // O(m)
            for (int i = 1; i < list.size(); i++) { // O(n)
                uf.union(list.get(i - 1), list.get(i));
            }
        }
        return uf.getMaxSize();
    }

    private static class UnionFind {

        private int[] root;
        private int[] rank;
        private int maxSize;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            this.maxSize = 1;
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
            this.maxSize = Math.max(this.maxSize,
                    Math.max(rank[rootX], rank[rootY]));
        }

        public int getMaxSize() {
            return this.maxSize;
        }
    }
}
