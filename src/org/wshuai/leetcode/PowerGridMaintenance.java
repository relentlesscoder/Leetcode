package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/09/2025.
 * #3607 https://leetcode.com/problems/power-grid-maintenance/
 */
public class PowerGridMaintenance {

    // time O(c + q), space O(c)
    public int[] processQueriesUnionFindReverseProcessing(int c, int[][] connections, int[][] queries) {
        int k = 0;
        int[] offline = new int[c + 1]; // offline[i] is total times station i goes offline
        for (int[] q : queries) { // O(q)
            if (q[0] == 2) {
                offline[q[1]]++;
            } else {
                k++;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int[] roots = new int[c + 1];
        int[] rank = new int[c + 1];
        Arrays.setAll(roots, i -> i); // O(c)
        Arrays.fill(rank, 1); // O(c)
        for (int[] conn : connections) {
            union(conn[0], conn[1], roots, rank); // O(α(c))
        }
        for (int i = 1; i <= c; i++) { // O(c)
            int r = find(i, roots); // O(α(c))
            if (r == i) {
                map.put(r, Integer.MAX_VALUE);
            }
        }
        for (int i = 1; i <= c; i++) { // O(c)
            // Do not add to map if station i ever went offline so
            // map stores minimum station id after all queries are
            // processed
            if (offline[i] > 0) {
                continue;
            }
            int r = find(i, roots); // O(α(c))
            int minVal = map.get(r);
            map.put(r, Math.min(minVal, i));
        }
        int[] res = new int[k];
        // Process queries reversely
        for (int i = queries.length - 1, j = k - 1; i >= 0; i--) { // O(q)
            int[] q = queries[i];
            int r = find(q[1], roots); // O(α(c))
            if (q[0] == 1) {
                if (offline[q[1]] == 0) { // Return i if station i is active
                    res[j] = q[1];
                } else { // Otherwise minimum station id for the group (or -1)
                    res[j] = map.get(r) == Integer.MAX_VALUE ? -1 : map.get(r);
                }
                j--;
            } else {
                if (--offline[q[1]] == 0) { // Station i goes "online"
                    int minVal = map.get(r);
                    map.put(r, Math.min(minVal, q[1])); // Update minimum station id for the group
                }
            }
        }
        return res;
    }

    // time O(n + c * log(c) + q * log(c)), space O(c)
    public int[] processQueriesUnionFind(int c, int[][] connections, int[][] queries) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        int[] roots = new int[c + 1];
        int[] rank = new int[c + 1];
        Arrays.setAll(roots, i -> i); // O(c)
        Arrays.fill(rank, 1); // O(c)
        // Union nodes for each edge
        for (int[] conn : connections) { // O(n)
            union(conn[0], conn[1], roots, rank); // O(α(c))
        }
        for (int i = 1; i <= c; i++) { // O(c)
            int r = find(i, roots); // O(α(c))
            if (r == i) {
                map.putIfAbsent(r, new TreeSet<>());
            }
        }
        for (int i = 1; i <= c; i++) { // O(c)
            int r = find(i, roots); // O(α(c))
            map.get(r).add(i); // O(log(c))
        }
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) { // O(q)
            int r = find(q[1], roots); // O(α(c))
            TreeSet<Integer> set = map.get(r); // O(log(c))
            if (q[0] == 1) {
                if (set.contains(q[1])) { // O(log(c))
                    res.add(q[1]);
                } else {
                    res.add(set.isEmpty() ? -1 : set.first());
                }
            } else {
                set.remove(q[1]); // O(log(c))
            }
        }
        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private void union(int x, int y, int[] roots, int[] rank) {
        int rootX = find(x, roots), rootY = find(y, roots);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] >= rank[rootY]) {
            rank[rootX] += rank[rootY];
            roots[rootY] = rootX;
        } else {
            rank[rootY] += rank[rootX];
            roots[rootX] = rootY;
        }
    }

    private int find(int x, int[] roots) {
        if (x != roots[x]) {
            roots[x] = find(roots[x], roots);
        }
        return roots[x];
    }
}
