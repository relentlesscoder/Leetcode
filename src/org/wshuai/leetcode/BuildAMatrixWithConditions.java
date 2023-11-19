package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/18/2023.
 * #2392 https://leetcode.com/problems/build-a-matrix-with-conditions/
 */
public class BuildAMatrixWithConditions {

    // time O(k), space O(k)
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] matrix = new int[k][k];
        int[] rowIndex = new int[k + 1], colIndex = new int[k + 1];
        sortBy(rowConditions, rowIndex, k);
        sortBy(colConditions, colIndex, k);
        Set<Integer> usedRows = new HashSet<>(), usedCols = new HashSet<>();
        for (int i = 1; i <= k; i++) {
            if (!usedRows.add(rowIndex[i]) || !usedCols.add(colIndex[i])) {
                return new int[0][];
            }
            matrix[rowIndex[i]][colIndex[i]] = i;
        }
        return matrix;
    }

    private void sortBy(int[][] conditions, int[] res, int k) {
        int index = 0;
        List<Integer>[] adj = new ArrayList[k + 1];
        int[] degree = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int[] c : conditions) {
            degree[c[1]]++;
            adj[c[0]].add(c[1]);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res[curr] = index++;
            for (int next : adj[curr]) {
                if (--degree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}
