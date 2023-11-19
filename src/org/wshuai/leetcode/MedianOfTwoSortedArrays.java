package org.wshuai.leetcode;

/**
 * Created by Wei on 08/09/2015.
 * #0004 https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {

	// time O(log(min(n1, n2)))
	// https://www.youtube.com/watch?v=LPFhl65R7ww
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n1 = nums1.length, n2 = nums2.length;
		if(n1 > n2){
			return findMedianSortedArrays(nums2, nums1);
		}
		int low = 0, high = n1;
		while(low <= high){
			// partitionX is the number of elements in the left partition of nums1
			int partitionX = low + (high - low) / 2;
			/*
			1 2 3 4 5 6 7 8 9 10 11
			1 2 3 4 5 6 7 8 9 10

			(11 + 1) / 2 = 6
			(10 + 1) / 2 = 5

			(len + 1) / 2 is the median for odd length array and left of two median
			numbers for even length array
			*/
			int partitionY = (n1 + n2 + 1) / 2 - partitionX;

			int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
			int minRightX = partitionX == n1 ? Integer.MAX_VALUE : nums1[partitionX];

			int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
			int minRightY = partitionY == n2 ? Integer.MAX_VALUE : nums2[partitionY];

			// maxLeftX <= minRightY
			// maxLeftX <= minRightX - true since nums1 is sorted
			// maxLeftY <= minRightX
			// maxLeftY <= minRightY - true since nums2 is sorted
			if(maxLeftX <= minRightY && maxLeftY <= minRightX){
				if((n1 + n2) % 2 == 0){
					return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) * 0.5;
				}else{
					return (double)Math.max(maxLeftX, maxLeftY);
				}
			}else if(maxLeftX > minRightY){
				// too many elements in the left side of num1 so move to the left to reduce partitionX
				high = partitionX - 1;
			}else{
				// too many elements in the left side of num2, since partitionY = (n1 + n2 + 1) / 2 - partitionX
				// we can move to the right to increase partitionX (reduce partitionY)
				low = partitionX + 1;
			}
		}
		// will never hit here
		return 0.0;
	}
}
