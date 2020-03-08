package org.wshuai.leetcode;

/**
 * Created by Wei on 07/24/2017.
 * #0321 https://leetcode.com/problems/create-maximum-number/
 */
public class CreateMaximumNumber {
	// time O((m + n)^3)
	// https://leetcode.com/problems/create-maximum-number/discuss/77285/Share-my-greedy-solution
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int n = nums1.length, m = nums2.length;
		int[] res = new int[k];
		for(int i = Math.max(0, k - m); i <= k && i <= n; i++){
			int[] cur = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
			if(greater(cur, 0, res, 0)){
				res = cur;
			}
		}
		return res;
	}

	private int[] maxArray(int[] nums, int k){
		int[] res = new int[k];
		int n = nums.length;
		for(int i = 0, j = 0; i < n; i++){
			while(n - i > k - j && j > 0 && nums[i] > res[j - 1]){
				j--;
			}
			if(j < k){
				res[j++] = nums[i];
			}
		}
		return res;
	}

	private boolean greater(int[] nums1, int i, int[] nums2, int j){
		while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]){
			i++;
			j++;
		}
		return j == nums2.length
				|| (i < nums1.length && nums1[i] > nums2[j]);
	}

	private int[] merge(int[] nums1, int[] nums2, int k){
		int[] res = new int[k];
		for(int i = 0, j = 0, r = 0; r < k; r++){
			res[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
		}
		return res;
	}
}
