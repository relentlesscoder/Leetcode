package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 10/14/2019.
 * #0737 https://leetcode.com/problems/sentence-similarity-ii/
 */
public class SentenceSimilarityII {

    // time O((m + n) * L), space O(m * L)
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2,
										  List<List<String>> similarPairs) {
        int n1 = sentence1.length, n2 = sentence2.length;
        if (n1 != n2) {
            return false;
        }
        UnionFind uf = new UnionFind(similarPairs);
        for (int i = 0; i < n1; i++) { // O(n)
            if (sentence1[i].equals(sentence2[i])) { // O(L)
                continue;
            }
            if (!uf.exists(sentence1[i])
					|| !uf.exists(sentence2[i])
					|| uf.find(sentence1[i]) != uf.find(sentence2[i])) { // α(m)
                return false;
            }
        }
        return true;
    }

    private static class UnionFind {

        Map<String, Integer> map;
        private int[] root;
        private int[] rank;

        public UnionFind(List<List<String>> similarPairs) {
            map = new HashMap<>();
            int j = 0;
            for (List<String> strs : similarPairs) { // O(m * L)
                for (String s : strs) {
                    if (!map.containsKey(s)) {
                        map.put(s, j);
                        j++;
                    }
                }
            }
            int m = map.size();
            root = new int[m];
            rank = new int[m];
            Arrays.fill(rank, 1); // O(m)
            Arrays.setAll(root, i -> i); // O(m)
            for (List<String> strs : similarPairs) {
                for (int i = 1; i < strs.size(); i++) { // O(m)
                    union(strs.get(i), strs.get(i - 1)); // α(m)
                }
            }
        }

        public boolean exists(String s) {
            return map.containsKey(s);
        }

        public int find(String s) {
            return find(map.get(s));
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

    // time O(n * m * max_len), space O(m * max_len)
    public boolean areSentencesSimilarTwoDFS(String[] sentence1, String[] sentence2,
                                             List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Map<String, Set<String>> adj = new HashMap<>();
        for (List<String> edge : similarPairs) {
            adj.computeIfAbsent(edge.get(0), value -> new HashSet<>()).add(edge.get(1));
            adj.computeIfAbsent(edge.get(1), value -> new HashSet<>()).add(edge.get(0));
        }
        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) {
                continue;
            }
            Set<String> visited = new HashSet<>();
            if (!dfs(sentence1[i], sentence2[i], visited, adj)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(String word, String target, Set<String> visited, Map<String, Set<String>> adj) {
        visited.add(word);
        if (word.equals(target)) {
            return true;
        }
        if (!adj.containsKey(word)) {
            return false;
        }
        for (String next : adj.get(word)) {
            if (!visited.contains(next) && dfs(next, target, visited, adj)) {
                return true;
            }
        }
        return false;
    }

    // time O(n * m * max_len), space O(m * max_len)
    public boolean areSentencesSimilarTwoBFS(String[] sentence1, String[] sentence2,
                                             List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }
        Map<String, Set<String>> adj = new HashMap<>();
        for (List<String> edge : similarPairs) {
            adj.computeIfAbsent(edge.get(0), value -> new HashSet<>()).add(edge.get(1));
            adj.computeIfAbsent(edge.get(1), value -> new HashSet<>()).add(edge.get(0));
        }
        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) {
                continue;
            }
            if (!adj.containsKey(sentence1[i]) || !adj.containsKey(sentence2[i])) {
                return false;
            }
            if (!bfs(sentence1[i], sentence2[i], adj)) {
                return false;
            }
        }
        return true;
    }

    private boolean bfs(String source, String target, Map<String, Set<String>> adj) {
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (!adj.containsKey(curr)) {
                continue;
            }
            for (String next : adj.get(curr)) {
                if (next.equals(target)) {
                    return true;
                }
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return false;
    }
}
