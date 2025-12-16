package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 11/22/2019.
 * #1258 https://leetcode.com/problems/synonymous-sentences/
 */
public class SynonymousSentences {

    // time O(m * L + m * log(m) + n * m * N), space O(m * L + n * N)
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        List<String> res = new ArrayList<>();
        UnionFind uf = new UnionFind(synonyms);
        String[] arr = text.split(" "); // O(n)
        dfs(0, new ArrayList<>(), arr, uf, res); // O((n + m) * N)
        return res;
    }

    private String buildString(List<String> words) {
        StringBuilder res = new StringBuilder();
        for (String w : words) {
            res.append(w).append(" ");
        }
        return res.substring(0, res.length() - 1);
    }

    private void dfs(int i, List<String> curr, String[] arr, UnionFind uf, List<String> res) {
        if (i == arr.length) {
            res.add(buildString(curr));
            return;
        }
        if (uf.exists(arr[i])) {
            TreeSet<String> words = uf.find(arr[i]);
            for (String w : words) {
                List<String> next = new ArrayList<>(curr);
                next.add(w);
                dfs(i + 1, next, arr, uf, res);
            }
        } else {
            List<String> next = new ArrayList<>(curr);
            next.add(arr[i]);
            dfs(i + 1, next, arr, uf, res);
        }
    }

    private static class UnionFind {

        Map<String, Integer> map;
        Map<Integer, String> reverseMap;
        Map<Integer, TreeSet<String>> groupMap;
        private int[] root;
        private int[] rank;

        public UnionFind(List<List<String>> synonyms) {
            map = new HashMap<>();
            reverseMap = new HashMap<>();
            int j = 0;
            for (List<String> strs : synonyms) { // O(m * L)
                for (String s : strs) {
                    if (!map.containsKey(s)) {
                        map.put(s, j);
                        reverseMap.put(j, s);
                        j++;
                    }
                }
            }
            int m = map.size();
            root = new int[m];
            rank = new int[m];
            Arrays.fill(rank, 1); // O(m)
            Arrays.setAll(root, i -> i); // O(m)
            for (List<String> strs : synonyms) {
                for (int i = 1; i < strs.size(); i++) { // O(m)
                    union(strs.get(i), strs.get(i - 1)); // α(m)
                }
            }
            groupMap = new HashMap<>();
            for (int i = 0; i < m; i++) { // O(m * log(m))
                int r = find(i);
                groupMap.computeIfAbsent(r, k -> new TreeSet<>()).add(reverseMap.get(i));
            }
        }

        public boolean exists(String s) {
            return map.containsKey(s);
        }

        public TreeSet<String> find(String s) {
            int r = find(map.get(s));
            return groupMap.get(r);
        }

        public void union(String s1, String s2) {
            union(map.get(s1), map.get(s2));
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
