package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/23/2017.
 * #0315 https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */
public class CountOfSmallerNumbersAfterSelf {
	private int[] bit;
	private int n;

	// time O(n*log(n)), space O(n)
	// https://www.youtube.com/watch?v=2SVLYsq5W8M
	// http://community.topcoder.com/i/education/binaryIndexedTrees/BITimg.gif
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		Map<Integer, Integer> map = new HashMap<>();
		int[] copy = nums.clone();
		// O(n*log(n))
		Arrays.sort(copy);
		int rank = 0;
		for(int i = 0; i < copy.length; i++){
			if(i == 0 || copy[i] != copy[i - 1]){
				map.put(copy[i], rank++);
			}
		}
		bit = new int[map.size() + 1];
		n = map.size();
		// O(n*log(k)), k is the number of distinct element
		for(int i = nums.length - 1; i >= 0; i--){
			// use rank as the index of binary indexed tree
			rank = map.get(nums[i]);
			// from tail to head find out how many nodes
			// in the BIT that are less than the current rank
			res.add(getSum(rank - 1));
			// insert the current rank
			update(rank, 1);
		}
		Collections.reverse(res);
		return res;
	}

	private int getSum(int index){
		int res = 0;
		index++;
		while(index > 0){
			res += bit[index];
			index -= (index & -index);
		}
		return res;
	}

	private void update(int index, int delta){
		index++;
		while(index <= n){
			bit[index] += delta;
			index += (index & -index);
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
