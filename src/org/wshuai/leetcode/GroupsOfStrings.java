package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 12/23/2025.
 * #2157 https://leetcode.com/problems/groups-of-strings/
 */
public class GroupsOfStrings {

    public int[] groupStrings(String[] words) {
        int n = words.length;
        Map<Integer, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            String w = words[i];
            int mask = 0;
            for (char c : w.toCharArray()) {
                mask += 1 << (c - 'a');
            }
            if (map.containsKey(mask)) { // 连通与当前字符串相重复的字符串
                uf.union(i, map.get(mask));
            } else {
                map.put(mask, i);
            }
        }
        for (int key : map.keySet()) {
            int idx = map.get(key);
            // 添加一个字母
            for (int i = 0; i < 26; i++) {
                if (((1 << i) & key) == 0) {
                    int val = key | (1 << i);
                    if (map.containsKey(val)) {
                        uf.union(idx, map.get(val));
                    }
                }
            }
            // 删除一个字母
            for (int i = 0; i < 26; i++) {
                if (((1 << i) & key) > 0) {
                    int val = key - (1 << i);
                    if (map.containsKey(val)) {
                        uf.union(idx, map.get(val));
                    }
                }
            }
            // 替换一个字母
            for (int i = 0; i < 26; i++) {
                if (((1 << i) & key) > 0) {
                    int v1 = key - (1 << i);
                    for (int j = 0; j < 26; j++) {
                        if (((1 << j) & v1) == 0) {
                            int v2 = v1 | (1 << j);
                            if (map.containsKey(v2)) {
                                uf.union(idx, map.get(v2));
                            }
                        }
                    }
                }
            }
        }
        return uf.getGroupInfo();
    }

    private static class UnionFind {

        private int count;
        private int max;
        private final int[] roots;
        private final int[] ranks;

        public UnionFind(int n) {
            this.count = n;
            this.max = 1;
            roots = new int[n];
            ranks = new int[n];
            Arrays.setAll(roots, i -> i);
            Arrays.fill(ranks, 1);
        }

        public int find(int x) {
            if (roots[x] != x) {
                roots[x] = find(roots[x]);
            }
            return roots[x];
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) {
                return;
            }
            if (ranks[rx] > ranks[ry]) {
                ranks[rx] += ranks[ry];
                roots[ry] = rx;
            } else {
                ranks[ry] += ranks[rx];
                roots[rx] = ry;
            }
            this.max = Math.max(this.max, Math.max(ranks[rx], ranks[ry]));
            this.count--;
        }

        public int[] getGroupInfo() {
            return new int[]{this.count, this.max};
        }
    }
}
