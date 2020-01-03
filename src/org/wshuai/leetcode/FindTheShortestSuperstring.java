package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 12/25/2019.
 * #943 https://leetcode.com/problems/find-the-shortest-superstring/
 */
public class FindTheShortestSuperstring {
	//https://www.youtube.com/watch?v=u_Wc4jwrp3Q
	public String shortestSuperstring(String[] A) {
		int N = A.length;
		int[][] dist = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				dist[i][j] = A[j].length();
				for(int k = 1; k <= Math.min(A[i].length(), A[j].length()); k++){
					if(A[i].substring(A[i].length() - k).equals(A[j].substring(0, k))){
						dist[i][j] = A[j].length() - k;
					}
				}
			}
		}
		int M = 1 << N;
		int[][] dp = new int[M][N];
		int[][] parent = new int[M][N];
		for(int i = 0; i < M; i++){
			Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
			Arrays.fill(parent[i], -1);
		}
		for(int i = 0; i < N; i++){
			dp[1 << i][i] = A[i].length();
		}
		for(int s = 1; s < M; s++){
			for(int i = 0; i < N; i++){
				if((s & (1 << i)) == 0){
					continue;
				}
				int prev = s - (1 << i);
				for(int j = 0; j < N; j++){
					if(dp[prev][j] + dist[j][i] < dp[s][i]){
						dp[s][i] = dp[prev][j] + dist[j][i];
						parent[s][i] = j;
					}
				}
			}
		}
		int min = Integer.MAX_VALUE;
		int end = -1;
		for(int i = 0; i < N; i++){
			if(dp[M - 1][i] < min){
				min = dp[M - 1][i];
				end = i;
			}
		}
		int s = M - 1;
		String res = "";
		while(s > 0){
			int prev = parent[s][end];
			if(prev < 0){
				res = A[end] + res;
			}else{
				res = A[end].substring(A[end].length() - dist[prev][end]) + res;
			}
			s -= (1 << end);
			end = prev;
		}
		return res;
	}
}
