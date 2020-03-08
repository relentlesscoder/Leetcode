package org.wshuai.leetcode;

/**
 * Created by Wei on 09/25/2016.
 * #0378 https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInASortedMatrix {
	// time O(log(n)*log(n))
	public int kthSmallest(int[][] matrix, int k) {
		int n = matrix.length, low = matrix[0][0], high = matrix[n - 1][n - 1];
		while(low < high){
			int mid = low + (high - low) / 2;
			int count = 0;
			for(int i = 0; i < n; i++){
				count += binarySearch(matrix[i], mid);
			}
			if(count < k){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return low;
	}

	private int binarySearch(int[] nums, int target){
		int left = 0, right = nums.length;
		while(left < right){
			int mid = left + (right - left) / 2;
			// need to count the target itself in
			if(nums[mid] <= target){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return left;
	}
}
