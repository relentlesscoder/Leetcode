package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/03/2017.
 * #0327 https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSum {

	// time O(n*log(n))
	public int countRangeSum(int[] nums, int lower, int upper) {
		if(nums == null || nums.length == 0 || lower > upper){
			return 0;
		}
		int index = 0, count = 0;
		long[] sum = new long[nums.length + 1], cand = new long[3 * sum.length + 1];
		cand[index++] = sum[0];
		cand[index++] = lower + sum[0] - 1;
		cand[index++] = upper + sum[0];

		for(int i = 1; i < sum.length; i++){
			sum[i] = sum[i - 1] + nums[i - 1];
			cand[index++] = sum[i];
			cand[index++] = lower + sum[i] - 1;
			cand[index++] = upper + sum[i];
		}

		cand[index] = Long.MIN_VALUE;
		Arrays.sort(cand);

		int[] bit = new int[cand.length];
		for(int i = 0; i < sum.length; i++){
			add(bit, Arrays.binarySearch(cand, sum[i]), 1);
		}
		for(int i = 0; i < sum.length; i++){
			add(bit, Arrays.binarySearch(cand, sum[i]), -1);

			count += query(bit, Arrays.binarySearch(cand, upper + sum[i]));

			count -= query(bit, Arrays.binarySearch(cand, lower + sum[i] - 1));
		}

		return count;
	}

	private void add(int[] bit, int index, int value){
		while(index < bit.length){
			bit[index] += value;
			index += (index & -index);
		}
	}

	private int query(int[] bit, int index){
		int sum = 0;
		while(index > 0){
			sum += bit[index];
			index -= (index & -index);
		}
		return sum;
	}

	// time O(n*(log(n))^2)
	public int countRangeSumMergeSort(int[] nums, int lower, int upper) {
		if(nums == null || nums.length == 0 || lower > upper){
			return 0;
		}
		return mergeSort(nums, 0, nums.length - 1, lower, upper);
	}

	private int mergeSort(int[] nums, int l, int r, int lower, int upper){
		if(l == r){
			return nums[l] >= lower && nums[r] <= upper ? 1 : 0;
		}
		int m = l + (r - l) / 2, count = 0;
		long[] arr = new long[r - m];
		long sum = 0;

		// find the prefix sum of the right part
		for(int i = m + 1; i <= r; i++){
			sum += nums[i];
			arr[i - (m + 1)] = sum;
		}
		// sort the prefix sum so that we can do binary search on it later
		Arrays.sort(arr);
		sum = 0;
		// time O(n*log(n))
		for(int i = m; i >= l; i--){
			sum += nums[i];
			// go from back to front on the left part
			// find the insertion point for upper - (left) sum and lower - (left) sum
			// the difference is the number of all right prefix sums that satisfy the criterion
			count += findIndex(arr, upper - sum + 0.5) - findIndex(arr, lower - sum - 0.5);
		}
		return mergeSort(nums, l, m, lower, upper) + mergeSort(nums, m + 1, r, lower, upper) + count;
	}

	private int findIndex(long[] arr, double val){
		int l = 0, r = arr.length, m = 0;
		while(l < r){
			m = l + (r - l) / 2;
			if(arr[m] < val){
				l = m + 1;
			}else{
				r = m;
			}
		}
		return l;
	}
}
