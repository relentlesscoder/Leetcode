package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Wei on 09/27/2023.
 * #2336 https://leetcode.com/problems/smallest-number-in-infinite-set/
 */
public class SmallestNumberInInfiniteSet {

    // time O(n * log(n)), space O(n)
    private static class SmallestInfiniteSet {

        private HashSet<Integer> popedNumbers;
        private PriorityQueue<Integer> popedNumbersMinQueue;
        private Integer currentMin;

        public SmallestInfiniteSet() {
            // 之前弹出后被加回来的数字的哈希表
            popedNumbers = new HashSet<>();
            // 之前弹出后被加回来的数字组成的最小队列
            popedNumbersMinQueue = new PriorityQueue<>();
            currentMin = 1;
        }

        public int popSmallest() {
            int smallest;
            // 现在被加回来的数字的最小队列里面找
            if (!popedNumbersMinQueue.isEmpty()) {
                smallest = popedNumbersMinQueue.poll();
                // 将数字从哈希表中删掉
                popedNumbers.remove(smallest);
            } else {
                // 如果之前弹出的数字都没有被加回来，则我们需要弹出并更新当前最小值
                smallest = currentMin;
                currentMin++;
            }
            return smallest;
        }

        public void addBack(int num) {
            // 如果数字被弹出但没有被加回来或者根本未被弹出
            if (popedNumbers.contains(num) || currentMin <= num) {
                return;
            }
            // 把数字加回来
            popedNumbers.add(num);
            popedNumbersMinQueue.offer(num);
        }
    }

    /**
     * Your SmallestInfiniteSet object will be instantiated and called as such:
     * SmallestInfiniteSet obj = new SmallestInfiniteSet();
     * int param_1 = obj.popSmallest();
     * obj.addBack(num);
     */
}
