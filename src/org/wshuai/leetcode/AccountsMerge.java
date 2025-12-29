package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Wei on 09/17/2019.
 * #0721 https://leetcode.com/problems/accounts-merge/
 */
public class AccountsMerge {

    // time O(n + m * log(m)), space O(n + m)
    public List<List<String>> accountsMergeUnionFind(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(accounts);
        return uf.merge();
    }

    private static class UnionFind {

        private final int n;
        private int[] root;
        private int[] rank;
        private String[] names;
        private Map<String, Integer> emailMap;
        private TreeSet<String>[] sets;

        public UnionFind(List<List<String>> accounts) {
            n = accounts.size();
            root = new int[n]; // O(n)
            rank = new int[n];
            names = new String[n];
            sets = new TreeSet[n]; // O(m) m is the total number of distinct email addresses
            Arrays.fill(rank, 1);
            Arrays.setAll(root, i -> i);
            Arrays.setAll(sets, i -> new TreeSet<>());
            emailMap = new HashMap<>();
            for (int i = 0; i < accounts.size(); i++) { // O(m)
                List<String> data = accounts.get(i);
                names[i] = data.get(0);
                for (int j = 1; j < data.size(); j++) {
                    String e = data.get(j);
                    if (emailMap.containsKey(e)) {
                        union(i, emailMap.get(e)); // O(α(n))
                    } else {
                        emailMap.put(e, i);
                    }
                }
            }
            for (String email : emailMap.keySet()) { // O(m * log(m))
                int root = find(email);
                sets[root].add(email);
            }
        }

        public List<List<String>> merge() {
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (sets[i].isEmpty()) {
                    continue;
                }
                List<String> data = new ArrayList<>();
                data.add(names[i]);
                for (String email : sets[i]) {
                    data.add(email);
                }
                res.add(data);
            }
            return res;
        }

        private int find(String s) {
            return find(emailMap.get(s));
        }

        private void union(String s1, String s2) {
            union(emailMap.get(s1), emailMap.get(s2));
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

    public List<List<String>> accountsMergeDFS(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        int n = accounts.size();
        boolean[][] adj = new boolean[n][n];
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> vals = accounts.get(i);
            for (int j = 1; j < vals.size(); j++) {
                String email = vals.get(j);
                if (map.containsKey(email)) {
                    adj[map.get(email)][i] = adj[i][map.get(email)] = true;
                }
                map.put(email, i);
            }
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            TreeSet<String> emails = new TreeSet<>();
            dfs(i, n, accounts, adj, emails, visited);
            List<String> cur = new ArrayList<>();
            cur.add(accounts.get(i).get(0));
            cur.addAll(emails);
            res.add(cur);
        }
        return res;
    }

    private void dfs(int i, int n, List<List<String>> accounts,
                     boolean[][] adj, TreeSet<String> emails, boolean[] visited) {
        visited[i] = true;
        List<String> account = accounts.get(i);
        for (int k = 1; k < account.size(); k++) {
            emails.add(account.get(k));
        }
        for (int j = 0; j < n; j++) {
            if (adj[i][j] && !visited[j]) {
                dfs(j, n, accounts, adj, emails, visited);
            }
        }
    }
}
