package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/16/2025.
 * #3551 https://leetcode.com/problems/minimum-swaps-to-sort-by-digit-sum/
 */
public class MinimumSwapsToSortByDigitSum {

    // time O(n * log(n)), space O(n)
    public int minSwapsUnionFind(int[] nums) {
        // 对于数组中大小为k的连通块，共需要k-1次操作来将连通块内的元素排序。
        // 示例1:
        //  0 1 2 3 4 5
        // [1,0,2,5,3,4]
        // [0,1,2,5,3,4]
        // [0,1,2,3,5,4]
        // [0,1,2,3,4,5]
        // 示例1中有三个联通块[1,0],[2],[5,3,4]所以需要最少需要三次操作。
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (num > 0) {
                arr[i][0] += num % 10;
                num /= 10;
            }
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            uf.union(i, arr[i][2]);
        }
        return n - uf.countComponents();
    }

    private class UnionFind {

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

    // time O(n * log(n)), space O(n)
    public int minSwaps(int[] nums) {
        int res = 0, n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (num > 0) {
                arr[i][0] += num % 10;
                num /= 10;
            }
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        // 循环排序
        for (int i = 0; i < n; i++) {
            while (arr[i][2] != i) {
                int temp = arr[arr[i][2]][2];
                arr[arr[i][2]][2] = arr[i][2];
                arr[i][2] = temp;
                res++;
            }
        }
        return res;
    }
}
