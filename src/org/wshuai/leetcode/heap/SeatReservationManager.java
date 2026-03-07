package org.wshuai.leetcode.heap;

import java.util.PriorityQueue;

/**
 * Created by Wei on 05/20/2021.
 * #1845 https://leetcode.com/problems/seat-reservation-manager/
 */
public class SeatReservationManager {

    // time O(k * log(k)), space O(k)
    private static class SeatManager {

        // #2336 的简化版
        private int min;
        private final PriorityQueue<Integer> minQueue;

        public SeatManager(int n) {
            min = 1;
            minQueue = new PriorityQueue<>();
        }

        public int reserve() {
            int res = -1;
            if (!minQueue.isEmpty()) {
                res = minQueue.poll();
            } else {
                res = min++;
            }
            return res;
        }

        public void unreserve(int seatNumber) {
            minQueue.offer(seatNumber);
        }
    }

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
}
