package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 03/05/2017.
 * #0532 https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class KDiffPairsInAnArray {
	// time O(n*log(n))
	public int findPairs(int[] nums, int k) {
		int res = 0, n = nums.length;
		Arrays.sort(nums);
		for(int i = 0, j = 0; i < n - 1; i++){
			if(i > 0 && nums[i] == nums[i - 1]){
				continue;
			}
			j = binarySearch(nums, Math.max(i + 1, j), n - 1, nums[i] + k);
			if(j != -1){
				res++;
			}
		}
		return res;
	}

	private int binarySearch(int[] nums, int low, int high, int target){
		while(low < high){
			int mid = low + (high - low) / 2;
			if(nums[mid] < target){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return nums[low] == target ? low : -1;
	}

	// time O(n)
	public int findPairsHashSet(int[] nums, int k) {
		int res = 0;
		Arrays.sort(nums);
		Set<Integer> visited = new HashSet<>(), used = new HashSet<>();
		for(int i = 0; i < nums.length; i++){
			if(visited.contains(nums[i] - k) && !used.contains(nums[i])){
				used.add(nums[i]);
				res++;
			}
			visited.add(nums[i]);
		}
		return res;
	}
}
