package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/23/2025.
 * #2459 https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space/
 */
public class SortArrayByMovingItemsToEmptySpace {

    // time O(n), space O(n)
    public int sortArray(int[] nums) {
        // #3551相似题
        int n = nums.length;
        UnionFind uf1 = new UnionFind(n);
        UnionFind uf2 = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            uf1.union(nums[i], nums[nums[i]]);
            uf2.union(nums[i], nums[(nums[i] - 1 + n) % n]);
        }
        return Math.min(uf1.getOps(), uf2.getOps());
    }

    private static class UnionFind {

        private int[] root;
        private int[] rank;

        public UnionFind(int n) {
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

        public int getOps() {
            int res = 0;
            // 如果置换环中包含0，则需要环的size - 1个操作；
            // 示例1:
            // [1,0,2,4,3] -> [1,2,3,4,0]
            // 共有三个环 [1],[0,2,3],[4], 0在环[0,2,3]中所以需要2次操作
            // 如果置换环中不包含0，则需要一个多余的操作把0加入环中，再需要环的size - 1个操作。
            // 示例1:
            // [1,3,2,4,0] -> [1,2,3,4,0]
            // 共有四个环 [1],[3,2],[4],[0], 0不在环[3,2]中所以：
            // 先需要一次操作把0加入环， 数组变为[1,0,2,4,3]
            // 然后根据示例一，还需要2此操作把环[0,2,3]置换
            for (int i = 0; i < root.length; i++) {
                if (root[i] == i && rank[i] > 1) {
                    if (find(0) != i) {
                        res += rank[i] + 1;
                    } else {
                        res += rank[i] - 1;
                    }
                }
            }
            return res;
        }
    }
}
