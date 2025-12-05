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
            // +1 since binary index tree starts with index 1
            int index = binarySearch(sorted, nums[i]) + 1;
            // Search in BIT that has "rank" strictly smaller than nums[i] (index - 1)
            int count = bit.query(index - 1);
            res.add(count);
            bit.add(index);
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

    // time O(n * log(n)), space O(n)
    public List<Integer> countSmallerMergeSort(int[] nums) {
        // https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        int[] count = new int[n], idx = new int[n];
        Arrays.setAll(idx, i -> i);
        mergeSort(nums, 0, n - 1, count, idx);
        for (int cnt : count) {
            res.add(cnt);
        }
        return res;
    }

    private void mergeSort(int[] nums, int low, int high, int[] count, int[] idx) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(nums, low, mid, count, idx);
        mergeSort(nums, mid + 1, high, count, idx);
        merge(nums, low, high, count, idx);
    }

	private void merge(int[] nums, int low, int high, int[] count, int[] idx) {
		int mid = low + (high - low) / 2;
		int leftIndex = low;
		int rightIndex = mid + 1;
		int nextIndex = mid + 1;
		int index = 0;
		int[] temp = new int[high - low + 1];
		while (leftIndex <= mid) {
			while (nextIndex <= high && nums[idx[leftIndex]] > nums[idx[nextIndex]]) {
				nextIndex++;
			}
			count[idx[leftIndex]] += nextIndex - mid - 1;
			// sorting
			while (rightIndex <= high && nums[idx[rightIndex]] < nums[idx[leftIndex]]) {
				temp[index++] = idx[rightIndex++];
			}
			temp[index++] = idx[leftIndex++];
		}
		while (rightIndex <= high) {
			// if right part still has elements left
			temp[index++] = idx[rightIndex++];
		}
		for (int i = low; i <= high; i++) {
			idx[i] = temp[i - low];
		}
	}
}
