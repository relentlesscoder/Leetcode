package org.wshuai.algorithm.sorting;

/**
 * Created by Wei on 9/12/16.
 */
public class QuickSort {
	public static void quickSort(int[] nums, int p, int q) {
		if (p < q) {
			int r = partition(nums, p, q);
			quickSort(nums, p, r - 1);
			quickSort(nums, r + 1, q);
		}
	}

	public static int partition(int[] nums, int p, int q) {
		int pivot = nums[q];
		int i = p;
		for (int j = p; j < q; j++) {
			if (nums[j] < pivot) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
			}
		}
		nums[q] = nums[i];
		nums[i] = pivot;
		return i;
	}
}
