package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #1191 https://leetcode.com/problems/k-concatenation-maximum-sum/
 */
public class KConcatenationMaximumSum {

	// fantastic explanation at https://www.youtube.com/watch?v=-T19A8DvD6U
	public int kConcatenationMaxSum(int[] arr, int k) {
		int mod = 1_000_000_007;
		int N = arr.length;
		long max1 = maxSubArray(arr);
		int[] arr1 = new int[N * 2];
		System.arraycopy(arr, 0, arr1, 0, N);
		System.arraycopy(arr, 0, arr1, N, N);
		long max2 = maxSubArray(arr1);
		long sum = 0;
		for(int a : arr){
			sum += a;
		}
		long max = Math.max(max1, Math.max(max2, (k - 2) * sum + max2));
		return (int)(max % mod);
	}

	//empty subarray allowed here, see #53 for the opposite
	private int maxSubArray(int[] nums) {
		int max = 0;
		int curr = 0;
		int len = nums.length;
		for(int i = 0; i < len; i++){
			curr = curr + nums[i];
			curr = Math.max(curr, 0);
			max = Math.max(curr, max);
		}
		return max;
	}
}
