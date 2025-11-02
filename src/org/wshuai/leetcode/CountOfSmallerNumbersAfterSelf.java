package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/23/2017.
 * #0315 https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {

	// time O(n * log(n)), space O(n)
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length;
		int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
		// +1 since binary index tree starts with index 1
		BIT bit = new BIT(sorted.length + 1);
		for (int i = n - 1; i >= 0; i--) {
			// Search in BIT that has "rank" that <= nums[i] - 1
			int count = bit.query(binarySearch(sorted, nums[i]));
			res.add(count);
			// +1 since binary index tree starts with index 1
			bit.add(binarySearch(sorted, nums[i]) + 1);
		}
		Collections.reverse(res);
		return res;
	}

	private int binarySearch(int[] nums, int target) {
		int low = 0, high = nums.length;
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (nums[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low;
	}

	private static class BIT {

		private int[] tree;

		public BIT(int n) {
			tree = new int[n];
		}

		public void add(int index) {
			while (index < tree.length) {
				tree[index]++;
				index += index & -index;
			}
		}

		public int query(int index) {
			int res = 0;
			while (index > 0) {
				res += tree[index];
				index -= index & -index;
			}
			return res;
		}
	}

	// time O(n*log(n)), space O(n)
	// https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
	public List<Integer> countSmallerMergeSort(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length;
		int[] count = new int[n], indexes = new int[n];
		for(int i = 0; i < n; i++){
			indexes[i] = i;
		}
		mergeSort(nums, indexes, count, 0, n - 1);
		for(int i = 0; i < n; i++){
			res.add(count[i]);
		}
		return res;
	}

	private void mergeSort(int[] nums, int[] indexes, int[] count, int start, int end){
		if(end <= start){
			return;
		}
		int mid = start + (end - start) / 2;
		mergeSort(nums, indexes, count, start, mid);
		mergeSort(nums, indexes, count, mid + 1, end);
		merge(nums, indexes, count, start, end);
	}

	private void merge(int[] nums, int[] indexes, int[] count, int start, int end){
		int mid = start + (end - start) / 2, leftIndex = start, rightIndex = mid + 1, rightCount = 0, sortIndex = 0;
		int[] newIndexes = new int[end - start + 1];
		while(leftIndex <= mid && rightIndex <= end){
			// if the value on the right side is smaller, increase the count
			if(nums[indexes[rightIndex]] < nums[indexes[leftIndex]]){
				newIndexes[sortIndex] = indexes[rightIndex];
				rightCount++;
				rightIndex++;
			// if the value on the right side is not smaller, add the count to the index
			// note that in the current merge, the right count is accumulative
			// example: left [4 7 9], right [2 3 5]
			// the right count for 4 is 2 and for 7 is 2 + 1 = 3
			}else{
				newIndexes[sortIndex] = indexes[leftIndex];
				count[indexes[leftIndex]] += rightCount;
				leftIndex++;
			}
			sortIndex++;
		}
		while(leftIndex <= mid){
			newIndexes[sortIndex] = indexes[leftIndex];
			count[indexes[leftIndex]] += rightCount;
			leftIndex++;
			sortIndex++;
		}
		while(rightIndex <= end){
			newIndexes[sortIndex++] = indexes[rightIndex++];
		}
		for(int i = start; i <= end; i++){
			indexes[i] = newIndexes[i - start];
		}
	}
}
