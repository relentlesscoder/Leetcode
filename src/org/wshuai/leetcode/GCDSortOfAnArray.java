package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/24/2025.
 * #1998 https://leetcode.com/problems/gcd-sort-of-an-array/
 */
public class GCDSortOfAnArray {

    // time O(n * sqrt(MAX) * log(MAX)), space O(n)
    public boolean gcdSort(int[] nums) {
        int n = nums.length;
        int[][] sorted = new int[n][2];
        Arrays.setAll(sorted, i -> new int[]{nums[i], i});
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        Map<Integer, List<Integer>> map = new HashMap<>();
        // #2521 计算每个元素的不同质因数
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    map.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
                    while (num % j == 0) {
                        num /= j;
                    }
                }
            }
            if (num > 1) {
                map.computeIfAbsent(num, k -> new ArrayList<>()).add(i);
            }
        }
        // 连通具有相同质因数的索引
        for (List<Integer> list : map.values()) {
            for (int i = 1; i < list.size(); i++) {
                uf.union(list.get(i - 1), list.get(i));
            }
        }
        // 如果排序之后的新索引都和排序前的旧索引在同一个连通分量中，则当前位置
        // 排序后的值一定可以通过与连通分量中另一个索引交换得到。如果每一个位置
        // 都满足这个条件，则排序是可能的。
        // 示例1:
        // [8,9,4,2,3]
        // 排序前的索引为[0,1,2,3,4], 连通集为[0,2,3]和[1,4]
        // 排序后的索引为[3,4,2,0,1], 可见每一对新旧索引都属于同一连通集。
        for (int i = 0; i < n; i++) {
            if (uf.find(i) != uf.find(sorted[i][1])) {
                return false;
            }
        }
        return true;
    }

    private static final class UnionFind {

        private final int[] roots;
        private final int[] ranks;

        public UnionFind(int n) {
            roots = new int[n];
            ranks = new int[n];
            Arrays.fill(ranks, 1);
            Arrays.setAll(roots, i -> i);
        }

        public int find(int x) {
            if (x != roots[x]) {
                roots[x] = find(roots[x]);
            }
            return roots[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            if (ranks[rx] >= ranks[ry]) {
                ranks[rx] += ranks[ry];
                roots[ry] = rx;
            } else {
                ranks[ry] += ranks[rx];
                roots[rx] = ry;
            }
        }
    }
}
