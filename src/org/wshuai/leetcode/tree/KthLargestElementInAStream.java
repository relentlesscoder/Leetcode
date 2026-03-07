package org.wshuai.leetcode.tree;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 08/21/2019.
 * #0703 https://leetcode.com/problems/kth-largest-element-in-a-stream/
 */
public class KthLargestElementInAStream {

    // time O(n * log(k)), space O(k)
    static final class KthLargest {

        private static final int MAX = (int) 1e4;
        private final int[] minQueue;

        public KthLargest(int k, int[] nums) {
            minQueue = new int[k];
            Arrays.fill(minQueue, -MAX);
            for (int i = 0; i < k && i < nums.length; i++) {
                minQueue[i] = nums[i];
            }
            for (int i = k / 2 - 1; i >= 0; i--) {
                sink(minQueue, i);
            }
            for (int i = k; i < nums.length; i++) {
                if (nums[i] > minQueue[0]) {
                    minQueue[0] = nums[i];
                    sink(minQueue, 0);
                }
            }
        }

        public int add(int val) {
            if (val > minQueue[0]) {
                minQueue[0] = val;
                sink(minQueue, 0);
            }
            return minQueue[0];
        }

        private void sink(int[] nums, int i) {
            int n = nums.length;
            while (i * 2 + 1 < n) {
                int j = i * 2 + 1;
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
    }

    // time O(n * log(k)), space O(k)
    private static class KthLargestPriorityQueue {

        private final int k;
        private final PriorityQueue<Integer> minQueue;

        public KthLargestPriorityQueue(int k, int[] nums) {
            this.k = k;
            minQueue = new PriorityQueue<>();
            for (int num : nums) {
                minQueue.offer(num);
                if (minQueue.size() > k) {
                    minQueue.poll();
                }
            }
        }

        public int add(int val) {
            minQueue.offer(val);
            if (minQueue.size() > k) {
                minQueue.poll();
            }
            return minQueue.peek();
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
