package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/2019.
 * #548 https://leetcode.com/problems/split-array-with-equal-sum/
 */
public class SplitArrayWithEqualSum {
	public boolean splitArray(int[] nums) {
		int N = nums.length;
		if(N < 7){
			return false;
		}
		int[] prefix = new int[N + 1];
		for(int i = 1; i <= N; i++){
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
		for(int i = 1; i + 5 < N; i++){
			// Skip 0 during calculate target because adding zero won't change it.
			if (i != 1 && nums[i - 1] == 0  && nums[i] == 0){
				continue;
			}
			int sum = prefix[i];
			if(dfs(sum, i + 1, 1, prefix, N)){
				return true;
			}
		}
		return false;
	}

	private boolean dfs(int target, int start, int count, int[] prefix, int N){
		if(start >= N){
			return false;
		}
		if(count == 3){
			return prefix[N] - prefix[start] == target;
		}
		for(int i = start + 1; i + 5 - count * 2 < N; i++){
			int sum = prefix[i] - prefix[start];
			if(sum != target){
				continue;
			}
			if(dfs(target, i + 1, count + 1, prefix, N)){
				return true;
			}
		}
		return false;
	}
}
