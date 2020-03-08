package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2016.
 * #0189 https://leetcode.com/problems/rotate-array/
 */
public class RotateArray {

	// time O(n), space O(1)
	public void rotate(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k <= 0){
			return;
		}
		int n = nums.length, count = 0;
		k %= n;
		for(int start = 0; count < n; start++){
			int cur = start;
			int prev = nums[start];
			do{
				int next = (cur + k) % n;
				int temp = nums[next];
				nums[next] = prev;
				cur = next;
				prev = temp;
				count++;
			}while(start != cur);
		}
	}

	// time O(n), space O(1)
	public void rotateReverse(int[] nums, int k) {
		int n = nums.length;
		k %= n;
		reverse(nums, 0, n - 1);
		reverse(nums, 0, k - 1);
		reverse(nums, k, n - 1);
	}

	private void reverse(int[] nums, int i, int j){
		while(i < j){
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
			i++;
			j--;
		}
	}
}
