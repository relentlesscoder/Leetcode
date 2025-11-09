package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/03/2017.
 * #0327 https://leetcode.com/problems/count-of-range-sum/
 */
public class CountOfRangeSum {

    // time O(n * log(m)), space O(n + m)
    public int countRangeSum(int[] nums, int lower, int upper) {
        int res = 0, n = nums.length;
        long[] prefix = new long[3 * n + 1];
        long curr = 0;
        for (int i = 0, j = 1; i < n; i++) {
            curr += nums[i];
            prefix[j++] = curr;
            prefix[j++] = curr - lower;
            prefix[j++] = curr - upper;
        }
        long[] sorted = Arrays.stream(prefix).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        int index = binarySearch(sorted, 0) + 1;
        bit.update(index, 1);
        long sum = 0;
        for (int num : nums) {
            sum += num;
            index = binarySearch(sorted, sum) + 1;
            int left = binarySearch(sorted, sum - upper) + 1;
            int right = binarySearch(sorted, sum - lower) + 1;
            res += bit.query(left, right);
            bit.update(index, 1);
        }
        return res;
    }

    private static class BIT {

        private final int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += index & -index;
            }
        }

        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }

        public int query(int left, int right) {
            return sum(right) - sum(left - 1);
        }
    }

	// time O(n * log(n)), space O(n)
	public int countRangeSumMergeSortPrefixSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] prefix = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			prefix[i] = prefix[i - 1] + nums[i - 1];
		}
		return mergeSort(prefix, 0, n, lower, upper);
	}

	private int mergeSort(long[] nums, int left, int right, int lower, int upper) {
		if (left >= right) {
			return 0;
		}
		int mid = left + (right - left) / 2;
		int leftCount = mergeSort(nums, left, mid, lower, upper);
		int rightCount = mergeSort(nums, mid + 1, right, lower, upper);
		int middleCount = merge(nums, left, right, lower, upper);
		return leftCount + rightCount + middleCount;
	}

	private int merge(long[] nums, int left, int right, int lower, int upper) {
		int res = 0;
		int mid = left + (right - left) / 2;
		int leftIndex = left;
		int rightIndex = mid + 1;
		int headIndex = mid + 1;
		int tailIndex = mid + 1;
		int index = 0;
		long[] temp = new long[right - left + 1];
		while (leftIndex <= mid) {
			while (tailIndex <= right && nums[tailIndex] - nums[leftIndex] < lower) {
				tailIndex++;
			}
			while (headIndex <= right && nums[headIndex] - nums[leftIndex] <= upper) {
				headIndex++;
			}
			res += headIndex - tailIndex;
			while (rightIndex <= right && nums[rightIndex] <= nums[leftIndex]) {
				temp[index++] = nums[rightIndex++];
			}
			temp[index++] = nums[leftIndex++];
		}
		while (rightIndex <= right) {
			temp[index++] = nums[rightIndex++];
		}
		for (int i = left; i <= right; i++) {
			nums[i] = temp[i - left];
		}
		return res;
	}

    // time O(n * (log(n))^2), space O(n)
    public int countRangeSumMergeSort(int[] nums, int lower, int upper) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1, lower, upper);
    }

    private int mergeSort(int[] nums, int left, int right, int lower, int upper) {
        if (left == right) {
            return nums[left] >= lower && nums[right] <= upper ? 1 : 0;
        }
        int mid = left + (right - left) / 2;
        int leftCount = mergeSort(nums, left, mid, lower, upper);
        int rightCount = mergeSort(nums, mid + 1, right, lower, upper);
        int middleCount = merge(nums, left, right, lower, upper);
        return leftCount + rightCount + middleCount;
    }

    private int merge(int[] nums, int left, int right, int lower, int upper) {
        int res = 0, mid = left + (right - left) / 2;
        long[] prefix = new long[right - mid];
        prefix[0] = nums[mid + 1];
        for (int i = 1, j = mid + 2; i < right - mid; i++, j++) { // O(n)
            prefix[i] = prefix[i - 1] + nums[j];
        }
        Arrays.sort(prefix); // n * log(n)
        long sum = 0;
        for (int i = mid; i >= left; i--) { // n * log(n)
            sum += nums[i];
            // l + r >= lower r >= lower - l (minus < lower - l)
            // l + r <= upper r <= upper - l (< upper - l + 1)
            res += binarySearch(prefix, upper - sum + 1) - binarySearch(prefix, lower - sum);
        }
        return res;
    }

    private int binarySearch(long[] nums, long target) {
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
}
