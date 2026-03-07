package org.wshuai.leetcode.stackqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 01/06/2026.
 * #3589 https://leetcode.com/problems/count-prime-gap-balanced-subarrays/
 */
public class CountPrimeGapBalancedSubarrays {

    private static final int MAX = 50000;
    private static final boolean[] NON_PRIME = new boolean[MAX + 1];

    static {
        NON_PRIME[0] = NON_PRIME[1] = true;
        for (int i = 2; i <= MAX; i++) {
            if (!NON_PRIME[i]) {
                for (int j = i; j <= MAX / i; j++) {
                    NON_PRIME[i * j] = true;
                }
            }
        }
    }

    // time O(n), space O(n)
    public int primeSubarray(int[] nums, int k) {
        int res = 0, n = nums.length, last = -1, last2 = -1;
        Deque<Integer> maxQueue = new ArrayDeque<>(), minQueue = new ArrayDeque<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (!NON_PRIME[nums[i]]) {
                last2 = last;
                last = i;
                while (!maxQueue.isEmpty() && nums[maxQueue.peekLast()] <= nums[i]) {
                    maxQueue.pollLast();
                }
                while (!minQueue.isEmpty() && nums[minQueue.peekLast()] >= nums[i]) {
                    minQueue.pollLast();
                }
                maxQueue.offer(i);
                minQueue.offer(i);
                while (nums[maxQueue.peek()] - nums[minQueue.peek()] > k) {
                    if (maxQueue.peek() == j) {
                        maxQueue.poll();
                    }
                    if (minQueue.peek() == j) {
                        minQueue.poll();
                    }
                    j++;
                }
            }
            // 如果当前窗口内最大质数 - 最小质数 <= k, 则含有至少一对质数的子数组都合法。last2 代表
            // 倒数第二个质数也就是以当前索引结尾的子数组开始索引的最大值 - 超过他子数组就只含有一个质
            // 数了。
            // 注意如果当前的最后两个质因数也不满足要求 - 最大质数 - 最小质数 > k，则 j 会前进到索引
            // last2 + 1 使得 last2 - j + 1 == 0。
            res += last2 - j + 1;
        }
        return res;
    }
}
