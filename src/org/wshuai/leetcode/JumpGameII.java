package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 11/09/2016.
 * #0045 https://leetcode.com/problems/jump-game-ii/
 */
public class JumpGameII {

	// time O(n)
	public int jump(int[] nums) {
		int max = 0, res = 0, cur = 0;
		for(int i = 0; i < nums.length; i++){
			// only advance to the next range if necessary
			if(i > cur){
				cur = max;
				res++;
			}
			max = Math.max(max, i + nums[i]);
		}
		return res;
	}

	// time O(n^2) TLE
	public int jumpDP(int[] nums) {
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = 0;
		for(int i = 1; i < n; i++){
			dp[i] = Integer.MAX_VALUE;
			for(int j = i - 1; j >= 0; j--){
				if(j + nums[j] >= i){
					dp[i] = Math.min(dp[j] + 1, dp[i]);
				}
			}
		}
		return dp[n - 1];
	}

	// TLE
	public int jumpBFS(int[] nums) {
		int n = nums.length;
		boolean[] visited = new boolean[n];
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offerLast(0);
		visited[0] = true;
		int res = 0;
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int cur = queue.pollFirst();
				int max = nums[cur];
				for(int i = 1; i <= max; i++){
					int next = cur + i;
					if(next == n - 1){
						return res + 1;
					}
					if(next < n && !visited[next]){
						visited[next] = true;
						queue.offerLast(next);
					}
				}
			}
			res++;
		}
		return 0;
	}
}
