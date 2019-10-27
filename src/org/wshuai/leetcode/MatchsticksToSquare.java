package org.wshuai.leetcode;

/**
 * Created by Wei on 10/27/19.
 * #473 https://leetcode.com/problems/matchsticks-to-square/
 */
public class MatchsticksToSquare {
	private int[] visited;

	// same as #698 Partition to K Equal Sum Subsets
	public boolean makesquare(int[] nums) {
		if(nums.length < 4){
			return false;
		}
		int sum = 0;
		for(int v: nums){
			sum += v;
		}
		if(sum % 4 != 0){
			return false;
		}
		visited = new int[nums.length];
		return dfs(nums, 0, 0, 4, sum/4);
	}

	private boolean dfs(int[] nums, int start, int curr, int k, int target){
		if(k == 1){
			return true;
		}
		if(curr == target){
			return dfs(nums, 0, 0, k-1, target);
		}
		for(int j = start; j < nums.length; j++){
			if(visited[j] == 1 || curr + nums[j] > target){
				continue;
			}
			visited[j] = 1;
			if(dfs(nums, j, curr + nums[j], k, target)){
				return true;
			}
			visited[j] = 0;
		}
		return false;
	}
}
