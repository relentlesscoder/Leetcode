package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/25/19.
 * #1102 https://leetcode.com/problems/path-with-maximum-minimum-value/
 */
public class PathWithMaximumMinimumValue {

	// BFS - Dijkstra MST
	public int maximumMinimumPath(int[][] A) {
		int m = A.length;
		int n = A[0].length;
		int[][] dir = new int[][]{
			{1, 0}, {-1, 0}, {0, 1}, {0, -1}
		};
		int res = Integer.MAX_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		pq.offer(new int[]{A[0][0], 0, 0});
		A[0][0] = -1;
		while(!pq.isEmpty()){
			int[] curr = pq.poll();
			res = Math.min(res, curr[0]);
			if(curr[1] == m - 1 && curr[2] == n - 1){
				return res;
			}
			for(int i = 0; i < 4; i++){
				int x = curr[1] + dir[i][0];
				int y = curr[2] + dir[i][1];
				if(x >= 0 && x < m && y >= 0 && y < n && A[x][y] >= 0){
					pq.offer(new int[]{A[x][y], x, y});
					A[x][y] = -1;
				}
			}
		}
		return -1;
	}

	// union find
	public int maximumMinimumPathDSU(int[][] A) {
		int[][] dir = new int[][]{
			{1, 0}, {-1, 0}, {0, 1}, {0, -1}
		};
		int m = A.length, n = A[0].length;
		DisjointSetUnion dsu = new DisjointSetUnion(m * n);

		List<int[]> vals = new ArrayList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				vals.add(new int[]{i, j, A[i][j]});
			}
		}
		Collections.sort(vals, (a, b) -> b[2] - a[2]);

		for(int i = 0; i < vals.size(); i++){
			int x = vals.get(i)[0], y = vals.get(i)[1], w = vals.get(i)[2];
			A[x][y] = -1;
			for(int j = 0; j < 4; j++){
				int a = x + dir[j][0];
				int b = y + dir[j][1];
				if(a >= 0 && a < m && b >= 0 && b < n && A[a][b] == -1){
					dsu.union(getIndex(a, b, n), getIndex(x, y, n));
				}
				if(dsu.isConnected(getIndex(0, 0, n), getIndex(m - 1, n - 1, n))){
					return w;
				}
			}
		}
		return -1;
	}

	private int getIndex(int x, int y, int n){
		return x * n + y;
	}
}
