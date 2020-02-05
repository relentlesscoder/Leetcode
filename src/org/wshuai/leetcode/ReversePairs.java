package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/23/2017.
 * #0493 https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {
	// time O(n*log(n))
	public int reversePairsMergeSort(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		return mergeSort(nums, 0, nums.length - 1);
	}

	private int mergeSort(int[] nums, int l, int r){
		if(l >= r){
			return 0;
		}
		int m = l + (r - l) / 2, i = l, j = m + 1, k = 0, p = m + 1;
		int[] merge = new int[r - l + 1];
		int res = mergeSort(nums, l, m) + mergeSort(nums, m + 1, r);
		while(i <= m){
			while(p <= r && nums[i] > 2L * nums[p]){
				p++;
			}
			res += p - m - 1;
			while(j <= r && nums[j] < nums[i]){
				merge[k++] = nums[j++];
			}
			merge[k++] = nums[i++];
		}
		while(j <= r){
			merge[k++] = nums[j++];
		}
		System.arraycopy(merge, 0, nums, l, r - l + 1);
		return res;
	}

	// time O(n*log(n)), space O(n)
	public int reversePairs(int[] nums) {
		if(nums == null || nums.length == 0){
			return 0;
		}
		int n = nums.length, res = 0;
		int[] sorted = new int[n], bit = new int[n + 1];
		System.arraycopy(nums, 0, sorted, 0, n);
		Arrays.sort(sorted);
		for(int i = 0; i < n; i++){
			res += i - getSum(bit, searchIndex(sorted, 2L*nums[i]));
			add(bit, searchIndex(sorted, nums[i]));
		}
		return res;
	}

	private int searchIndex(int[] nums, long t){
		int left = 0, right = nums.length - 1;
		while(left < right){
			int mid = left + (right - left) / 2;
			if(nums[mid] <= t){
				left = mid + 1;
			}else{
				right = mid;
			}
		}
		return nums[left] <= t ? left : left - 1;
	}

	private int getSum(int[] bit, int index){
		int sum = 0;
		index++;
		while(index > 0){
			sum += bit[index];
			index -= (index & -index);
		}
		return sum;
	}

	private void add(int[] bit, int index){
		index++;
		while(index < bit.length){
			bit[index] += 1;
			index += (index & -index);
		}
	}
}