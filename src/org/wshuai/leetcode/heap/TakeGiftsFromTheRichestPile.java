package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/17/2023.
 * #2558 https://leetcode.com/problems/take-gifts-from-the-richest-pile/
 */
public class TakeGiftsFromTheRichestPile {

    // time O(n + k * log(n)), space O(1)
    public long pickGiftsHeapify(int[] gifts, int k) {
        long res = 0;
        int n = gifts.length;
        // 原地堆化（最大堆）
        // 堆化可以保证 h[0] 是堆顶元素，且 h[i] >= max(h[2 * i + 1], h[2 * i + 2])
        // 倒着遍历，从而保证 i 的左右子树一定是堆，那么 sink(h, i) 就可以把左右子树合并成一个堆
        // 下标 >= h.length / 2 的元素是二叉树的叶子，无需下沉
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(gifts, i);
        }
        while (k-- > 0 && gifts[0] > 1) {
            gifts[0] = (int) Math.sqrt(gifts[0]);
            sink(gifts, 0);
        }
        for (int x : gifts) {
            res += x;
        }
        return res;
    }

    private void sink(int[] nums, int i) {
        // 把 h[i] 不断下沉，每次找左右儿子中最大的交换，直到 i 的左右儿子都 <= h[i] 时停止
        int n = nums.length;
        while (i * 2 + 1 < n) {
            int j = i * 2 + 1; // i 的左儿子
            if (j + 1 < n && nums[j + 1] > nums[j]) { // i 的右儿子比 i 的左儿子大
                j++;
            }
            if (nums[j] <= nums[i]) { // 说明 i 的左右儿子都 <= h[i]，停止下沉
                break;
            }
            // 下沉
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }

    // time O(n * log(n)), space O(n)
    public long pickGiftsPriorityQueue(int[] gifts, int k) {
        long res = 0;
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : gifts) {
            res += num;
            maxQueue.offer(num);
        }
        while (k-- > 0) {
            int curr = maxQueue.poll(), x = (int) Math.sqrt(curr);
            res -= curr - x;
            maxQueue.offer(x);
        }
        return res;
    }
}
