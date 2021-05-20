package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 05/20/2021.
 * #1845 https://leetcode.com/problems/seat-reservation-manager/
 */
public class SeatReservationManager {

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    public SeatReservationManager(int n) {
        for(int i = 1; i <= n; i++){
            pq.offer(i);
        }
    }

    // time O(log(n))
    public int reserve() {
        return pq.poll();
    }

    // time O(log(n))
    public void unreserve(int seatNumber) {
        pq.offer(seatNumber);
    }
}
