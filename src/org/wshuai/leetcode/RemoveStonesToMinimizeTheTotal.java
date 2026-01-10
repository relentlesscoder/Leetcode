package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/25/2023.
 * #1962 https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 */
public class RemoveStonesToMinimizeTheTotal {

    // time O(n + k * log(n)), space O(1)
    public int minStoneSumArray(int[] piles, int k) {
        // #2558 原地 heapify 原数组
        int res = 0, n = piles.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(piles, i);
        }
        while (k-- > 0) {
            piles[0] -= piles[0] / 2;
            sink(piles, 0);
        }
        for (int x : piles) {
            res += x;
        }
        return res;
    }

    private void sink(int[] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1] > nums[j]) {
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
    public int minStoneSumPriorityQueue(int[] piles, int k) {
        // 贪心: 维护一个最大堆，每次从堆中取出最大值来减小让数组和最小化。
        int res = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int p : piles) {
            maxQueue.offer(p);
        }
        while (k-- > 0 && maxQueue.peek() > 1) {
            int max = maxQueue.poll();
            maxQueue.offer(max - max / 2);
        }
        while (!maxQueue.isEmpty()) {
            res += maxQueue.poll();
        }
        return res;
    }
}
