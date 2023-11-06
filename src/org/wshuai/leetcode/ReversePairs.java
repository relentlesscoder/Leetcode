package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 07/23/2017.
 * #0493 https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {

	// time O(n*log(n))
	public int reversePairsMergeSort(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		return mergeSort(nums, 0, nums.length - 1);
	}

	private int mergeSort(int[] nums, int l, int r) {
		if (l >= r) {
			return 0;
		}
		int m = l + (r - l) / 2, i = l, j = m + 1, k = 0, p = m + 1;
		int[] merge = new int[r - l + 1];
		int res = mergeSort(nums, l, m) + mergeSort(nums, m + 1, r);
		// since both left and right part are sorted, the reverse pair
		// can be carried over when we iterate the left part.
		// for example, we have left: [10, 12, 15], right[3, 4, 5]
		// for 10, it can combine with 3 and 4 to form a reverse pair
		// for 12, since it is greater than or equal to 10, it can also
		// combine with 3 and 4 to form a reverse pair. and plus the 5
		// there will be total 3 reverse pairs. Then for the same reason,
		// 15 will also have 3 reverse pairs.
		while (i <= m) {
			while (p <= r && nums[i] > 2L * nums[p]) {
				p++;
			}
			res += p - m - 1;
			// sorting
			while (j <= r && nums[j] < nums[i]) {
				merge[k++] = nums[j++];
			}
			merge[k++] = nums[i++];
		}
		while (j <= r) {
			// if right part still has elements left
			merge[k++] = nums[j++];
		}
		System.arraycopy(merge, 0, nums, l, r - l + 1);
		return res;
	}

	// time O(n*log(n)), space O(n)
	public int reversePairs(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// General idea:
		// For each of the element, find it's index using binary search then
		// save the frequency to the bit. Then iterate the array, for each of
		// the number nums[i], find the frequency of element that are already
		// in the bit that are less or equal to 2*nums[i] and add it to the
		// answer.
		int n = nums.length, res = 0;
		int[] sorted = new int[n], bit = new int[n + 1];
		System.arraycopy(nums, 0, sorted, 0, n);
		Arrays.sort(sorted);
		for (int i = 0; i < n; i++) {
			// Use i here because i is not yet added to the bit
			// so when we are at i = 4, there is only 3 elements in
			// bit. Here in the BIT we are searching for values prior to i
			// that are less than or equals to 2*nums[i].
			res += i - getSum(bit, searchIndex(sorted, 2L * nums[i]));
			// Add nums[i]'s index in the sorted array to the BIT
			add(bit, searchIndex(sorted, nums[i]));
		}
		return res;
	}

	private int searchIndex(int[] nums, long t) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= t) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return nums[left] <= t ? left : left - 1;
	}

	private int getSum(int[] bit, int index) {
		int sum = 0;
		index++;
		while (index > 0) {
			sum += bit[index];
			index -= (index & -index);
		}
		return sum;
	}

	private void add(int[] bit, int index) {
		index++;
		while (index < bit.length) {
			bit[index] += 1;
			index += (index & -index);
		}
	}
}