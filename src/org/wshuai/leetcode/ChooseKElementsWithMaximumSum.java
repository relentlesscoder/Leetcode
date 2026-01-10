package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 01/09/2026.
 * #3478 https://leetcode.com/problems/choose-k-elements-with-maximum-sum/
 */
public class ChooseKElementsWithMaximumSum {

    // time O(n * log(n)), space O(n)
    public long[] findMaxSumArray(int[] nums1, int[] nums2, int k) {
        // 优化: 用数组代替优先队列
        int n = nums1.length;
        long[] res = new long[n];
        Integer[] arr = new Integer[n];
        Arrays.setAll(arr, i -> i);
        Arrays.sort(arr, (a, b) -> nums1[a] != nums1[b] ? nums1[a] - nums1[b] : a - b);
        int[] minQueue = new int[k];
        Arrays.fill(minQueue, 0);
        long sum = 0;
        for (int i = 0; i < n; i++) {
            int idx = arr[i], val = nums1[idx];
            if (i > 0 && val == nums1[arr[i - 1]]) {
                res[idx] = res[arr[i - 1]];
            } else {
                res[idx] = sum;
            }
            // 仅有当前值大于队列中的最小值才入列
            if (nums2[idx] > minQueue[0]) {
                sum += nums2[idx];
                sum -= minQueue[0];
                minQueue[0] = nums2[idx];
                sink(minQueue, 0);
            }
        }
        return res;
    }

    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] < nums[j]) {
                j++;
            }
            if (nums[j] >= nums[i]) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }

    // time O(n * log(n)), space O(n)
    public long[] findMaxSumPriorityQueue(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        long[] res = new long[n];
        // 将 nums1 的索引按照值大小排序，则排序后当前位置 i 左边所有与当前索引值不同的
        // 索引 j 的值 nums1[j] 都小于 nums1[i]。
        Integer[] arr = new Integer[n];
        Arrays.setAll(arr, i -> i); // O(n)
        Arrays.sort(arr, (a, b) -> nums1[a] != nums1[b] ? nums1[a] - nums1[b] : a - b); // O(n * log(n))
        // 维护一个大小为 k 的最小堆存最多 k 个最大值
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        long sum = 0;
        // 当我们按照排序后的顺序遍历索引则左边的 k 个来自 nums2 的最大值的和就是答案
        for (int i = 0; i < n; i++) { // O(n)
            int idx = arr[i], val = nums1[idx];
            // 如果值与前一个索引的值相同则答案也跟前一个索引的相同
            if (i > 0 && val == nums1[arr[i - 1]]) {
                res[idx] = res[arr[i - 1]];
            } else {
                res[idx] = sum;
            }
            // sum 代表当前 k 个最大值的和
            sum += nums2[idx];
            // 将当前值入列
            minQueue.offer(nums2[idx]); // O(log(n))
            // 如果队列中的值超过 k 个，弹出最小的值
            if (minQueue.size() > k) {
                // 更新 sum 的值减去出列的值
                sum -= minQueue.poll();
            }
        }
        return res;
    }
}
