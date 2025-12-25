package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/24/2025.
 * #2709 https://leetcode.cn/problems/greatest-common-divisor-traversal/
 */
public class GreatestCommonDivisorTraversal {

    private static final int MAX = (int) 1e5;
    private static final List<Integer>[] PRIME = new ArrayList[MAX + 1];

    // 对[2,MAX]中的数进行预处理找到每个数的所有不同质因数
    static {
        Arrays.setAll(PRIME, i -> new ArrayList<>());
        for (int i = 2; i <= MAX; i++) {
            if (PRIME[i].isEmpty()) {
                for (int j = i; j <= MAX; j += i) {
                    PRIME[j].add(i);
                }
            }
        }
    }

    // time O(n * sqrt(MAX)), space O(MAX)
    public boolean canTraverseAllPairsPreProcessing(int[] nums) {
        int n = nums.length, max = 0;
        if (n == 1) {
            return true;
        }
        for (int num : nums) {
            max = Math.max(max, num);
        }
        UnionFind uf = new UnionFind(max + 1);
        for (int i = 0; i < n; i++) { // O(n)
            if (nums[i] == 1) { // 根据题目描述可知1不与任何其他数字连通
                return false;
            }
            int num = nums[i];
            List<Integer> list = PRIME[num];
            // 将当前原素和它所有的质因数连通
            for (int j = 0; j < list.size(); j++) { // O(sqrt(MAX))
                uf.union(num, list.get(j));
            }
        }
        // 判断数组中的数是否属于相同的连通分量，注意这里不能直接判断连通分量的
        // 个数因为不是所有区间[2,MAX]中的数都存在于原数组中。
        int root = uf.find(nums[0]);
        for (int i = 0; i < n; i++) { // O(n)
            if (uf.find(nums[i]) != root) {
                return false;
            }
        }
        return true;
    }

    // time O(n * sqrt(MAX) * log(MAX)), space O(n)
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return true;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        // #2521 计算每个元素的不同质因数
        for (int i = 0; i < n; i++) { // O(n)
            if (nums[i] == 1) { // 根据题目描述可知1不与任何其他数字连通
                return false;
            }
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
        // 判断连通集是否只有一个连通分量
        return uf.countComponents() == 1;
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
}
