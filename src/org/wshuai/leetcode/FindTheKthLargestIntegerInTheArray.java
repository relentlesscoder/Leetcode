package org.wshuai.leetcode;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Wei on 08/06/2025.
 * #1985 https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
 */
public class FindTheKthLargestIntegerInTheArray {

    // time O(n * log(k)), space O(k)
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> minQueue = new PriorityQueue<>((a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return Integer.compare(a.length(), b.length());
        });
        for (String num : nums) {
            minQueue.offer(num);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
        }
        return minQueue.peek();
    }

    // time O(n), space O(n)
    public String kthLargestNumberQuickSelect(String[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int pivot = partition(nums, low, high);
            if (pivot == k - 1) {
                return nums[pivot];
            } else if (pivot < k - 1) {
                low = pivot + 1;
            } else {
                high = pivot - 1;
            }
        }
        return nums[k - 1];
    }

    private int partition(String[] nums, int low, int high) {
        int pivot = low + (new Random()).nextInt(high - low + 1);
        swap(nums, pivot, high);
        pivot = low;
        for (int i = low; i < high; i++) {
            if (nums[i].length() > nums[high].length()
                    || (nums[i].length() == nums[high].length()
                    && nums[i].compareTo(nums[high]) >= 0)) {
                String temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot++] = temp;
            }
        }
        swap(nums, pivot, high);
        return pivot;
    }

    private void swap(String[] nums, int pivot, int high) {
        String temp = nums[high];
        nums[high] = nums[pivot];
        nums[pivot] = temp;
    }
}
