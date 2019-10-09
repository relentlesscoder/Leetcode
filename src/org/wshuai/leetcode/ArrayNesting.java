package org.wshuai.leetcode;

/**
 * Created by Wei on 10/9/2019.
 * #565 https://leetcode.com/problems/array-nesting/
 */
public class ArrayNesting {
	public int arrayNesting(int[] nums) {
		int res = 0;
		boolean[] visited = new boolean[nums.length];
		for(int i = 0; i < nums.length; i++){
			if(visited[i]){
				continue;
			}
			int count = 0;
			int j = i;
			while(nums[j] != i){
				visited[j] = true;
				j = nums[j];
				count++;
			}
			res = Math.max(res, count + 1);
		}
		return res;
	}
}
