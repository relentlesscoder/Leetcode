package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/13/2020.
 * #1584 https://leetcode.com/problems/min-cost-to-connect-all-points/
 */
public class MinCostToConnectAllPoints {

    // time O(E*log(E)), space O(E), E = n^2
    public int minCostConnectPointsPrim(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int res = 0, count = 1, n = points.length;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for(int i = 1; i < n; i++){
            pq.offer(new int[]{dist(points[0], points[i]), 0, i});
        }
        while(!pq.isEmpty() && count < n){
            int[] cur = pq.poll();
            if(visited[cur[2]]){
                continue;
            }
            visited[cur[2]] = true;
            count++;
            res += cur[0];

            for(int i = 0; i < n; i++){
                if(i == cur[2] || visited[i]){
                    continue;
                }
                pq.offer(new int[]{dist(points[cur[2]], points[i]), cur[2], i});
            }
        }
        return res;
    }

    // time O(E*log(E)), space O(E), E = n^2
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int res = 0, n = points.length, count = n;
        int[] root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
            for(int j = i + 1; j < n; j++){
                pq.offer(new int[]{dist(points[i], points[j]), i, j});
            }
        }
        while(!pq.isEmpty() && count > 1){
            int[] cur = pq.poll();
            int u = find(root, cur[1]), v = find(root, cur[2]);
            if(u == v){
                continue;
            }
            count--;
            res += cur[0];
            root[v] = u;
        }
        return res;
    }

    private int dist(int[] p1, int[] p2){
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private int find(int[] root, int r){
        if(r != root[r]){
            root[r] = find(root, root[r]);
        }
        return root[r];
    }
}
