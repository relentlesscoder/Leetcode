package org.wshuai.leetcode;

/**
 * Created by Wei on 9/25/19.
 * #698 https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */
public class PartitionToKEqualSumSubsets {
	private int[] visited;

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for(int v: nums){
			sum += v;
		}
		if(sum % k != 0){
			return false;
		}
		visited = new int[nums.length];
		return dfs(nums, 0, 0, k, sum/k);
	}

	private boolean dfs(int[] nums, int start, int curr, int k, int target){
		if(k == 1){
			return true;
		}
		if(curr > target){
			return false;
		}
		if(curr == target){
			return dfs(nums, 0, 0, k-1, target);
		}
		for(int j = start; j < nums.length; j++){
			if(visited[j] == 1){
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
