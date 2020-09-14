package org.wshuai.leetcode;

/**
 * Created by Wei on 10/22/2019.
 * #0215 https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInAnArray {

	// time - average: O(n * log(n)) worst: O(n^2)
	// after randomization, expected: O (n)
	public int findKthLargest(int[] nums, int k) {
		return getKthLargest(nums, 0, nums.length - 1, k);
	}

	private int getKthLargest(int[] nums, int left, int right, int k){
		if(k > 0 && k <= right - left + 1){
			int pivot = partition(nums, left, right);
			int count = pivot - left + 1;
			if(count == k){
				return nums[pivot];
			}else if(count > k){
				return getKthLargest(nums, left, pivot - 1, k);
			}else{
				return getKthLargest(nums, pivot + 1, right, k - count);
			}
		}
		return Integer.MAX_VALUE;
	}

	public int findKthLargestIterative(int[] nums, int k) {
		int n = nums.length, left = 0, right = n - 1;
		while(left <= right){
			int mid = partition(nums, left, right);
			if(mid == k - 1){
				break;
			}
			if(mid < k - 1){
				left = mid + 1;
			}else{
				right = mid - 1;
			}
		}
		return nums[k - 1];
	}

	private int partition(int[] nums, int left, int right){
		// randomization 33ms -> 3ms
		int index = left + (int)(Math.random()*(right - left + 1));
		int val = nums[index];
		nums[index] = nums[right];
		nums[right] = val;
		int p = left;
		for(int i = left; i < right; i++){
			if(nums[i] >= val){
				int temp = nums[p];
				nums[p++] = nums[i];
				nums[i] = temp;
			}
		}
		nums[right] = nums[p];
		nums[p] = val;
		return p;
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
