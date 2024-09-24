package org.wshuai.leetcode;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Wei on 10/22/2019.
 * #0215 https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestElementInAnArray {

    // time O(n * log(k)), space O(k)
    public int findKthLargestMinQueue(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (int num : nums) {
            minQueue.offer(num);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }
        return minQueue.peek();
    }

    // time - average: O(n * log(n)); worst: O(n^2); expected: O (n) with randomization, space O(n)
    public int findKthLargestQuickSelect(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int pivot = partition(nums, low, high);
            if (pivot == k - 1) {
                break;
            }
            if (pivot < k - 1) {
                low = pivot + 1;
            } else {
                high = pivot - 1;
            }
        }
        return nums[k - 1];
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = low + (new Random()).nextInt(high - low + 1);
        swap(nums, pivot, high);
        pivot = low;
        for (int i = low; i < high; i++) {
            if (nums[i] >= nums[high]) {
                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot++] = temp;
            }
        }
        swap(nums, pivot, high);
        return pivot;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
