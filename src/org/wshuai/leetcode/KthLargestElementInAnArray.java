package org.wshuai.leetcode;

import java.util.Random;

/**
 * Created by Wei on 10/22/2019.
 * #0215 https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInAnArray {

	// time - average: O(n * log(n)) worst: O(n^2)
	// after randomization, expected: O (n)
	public int findKthLargest(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
		while(left <= right){
			int mid = partition(nums, left, right);
			if(mid == k - 1){
				return nums[mid];
			}
			if(mid < k - 1){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return -1;
	}

	private int partition(int[] nums, int left, int right){
		int index = left + (new Random()).nextInt(right - left + 1);
		swap(nums, index, right);
		int pivot = left;
		for(int i = left; i < right; i++){
			if(nums[i] >= nums[right]){
				int temp = nums[i];
				nums[i] = nums[pivot];
				nums[pivot++] = temp;
			}
		}
		swap(nums, pivot, right);
		return pivot;
	}

	private void swap(int[] nums, int i, int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/*
	 * deterministic quick selection
	private int getKthLargestUtil(int[] nums, int k, int l, int r){
		if(k > 0 && k <= r - l + 1){
			int n = r - l + 1;

			int i;

			int[] median = new int[(n + 4) / 5];
			for(i = 0; i < n/5; i++){
				median[i] = findMedian(nums, l + i * 5, 5);
			}

			if(i * 5 < n){
				median[i] = findMedian(nums, l + i * 5, n % 5);
				i++;
			}

			int medOfMed = (i == 1) ? median[i - 1] : getKthLargestUtil(median, 0, i - 1, i / 2);

			int p = partition(nums, l, r, medOfMed);
			int count = p - l + 1;
			if(count == k){
				return nums[p];
			}else if(count > k){
				return getKthLargestUtil(nums, k, l, p - 1);
			}else{
				return getKthLargestUtil(nums, k - count, p + 1, r);
			}
		}
		return Integer.MAX_VALUE;
	}

	private int partition(int[] nums, int l, int r, int x){

		int k;
		for(k = l; k < r; k++){
			if(nums[k] == x){
				break;
			}
		}
		int temp = nums[k];
		nums[k] = nums[r];
		nums[r] = temp;

		int pivot = nums[r];
		int i = l;
		for(int j = l; j < r; j++){
			int val = nums[j];
			if(val > pivot){
				nums[j] = nums[i];
				nums[i++] = val;
			}
		}
		nums[r] = nums[i];
		nums[i] = pivot;
		return i;
	}

	private int findMedian(int[] arr, int i, int n){
		Arrays.sort(arr, i, i + n);
		return arr[i + n / 2];
	}*/
}
