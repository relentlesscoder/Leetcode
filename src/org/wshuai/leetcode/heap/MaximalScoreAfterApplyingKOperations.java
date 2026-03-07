package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 01/08/2026.
 * #2530 https://leetcode.com/problems/maximal-score-after-applying-k-operations/
 */
public class MaximalScoreAfterApplyingKOperations {

    // time O(n + k * log(n)), space O(1)
    public long maxKelementsArray(int[] nums, int k) {
        // #2558 原地 heapify 原数组
        long res = 0;
        int n = nums.length;
        for (int i = n / 2 - 1; i >= 0; i--) { // O(n)
            sink(nums, i);
        }
        while (k-- > 0) { // O(k)
            int curr = nums[0];
            res += curr;
            nums[0] = (curr + 2) / 3;
            sink(nums, 0);
        }
        return res;
    }

    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] >= nums[j]) {
                j = j + 1;
            }
            if (nums[j] <= nums[i]) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }

    // time O((n + k) * log(n)), space O(n)
    public long maxKelementsPriorityQueue(int[] nums, int k) {
        // 贪心: 维护一个最大堆，每次从堆中取出最大值最大化最后得分。
        long res = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            maxQueue.offer(num);
        }
        while (k-- > 0) {
            int curr = maxQueue.poll();
            res += curr;
            // 注意这里需要求 ceiling
            maxQueue.offer((curr + 2) / 3);
        }
        return res;
    }
}
