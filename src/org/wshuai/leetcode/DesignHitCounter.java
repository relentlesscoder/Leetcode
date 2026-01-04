package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/31/2016.
 * #0362 https://leetcode.com/problems/design-hit-counter/
 */
public class DesignHitCounter {

    // time O(n), space O(300)
    private static class HitCounterArray {

        private final int[] times;
        private final int[] hits;

        public HitCounterArray() {
            this.times = new int[300];
            this.hits = new int[300];
        }

        public void hit(int timestamp) {
            int idx = (timestamp - 1) % 300;
            if (times[idx] != timestamp) {
                times[idx] = timestamp;
                hits[idx] = 1;
            } else {
                hits[idx]++;
            }
        }

        public int getHits(int timestamp) {
            int count = 0;
            for (int i = 0; i < 300; i++) {
                if (times[i] > timestamp - 300) {
                    count += hits[i];
                }
            }
            return count;
        }
    }

    // time O(n), space O(300)
    private static class HitCounterQueue {

        private int count;
        private final Deque<int[]> queue;

        public HitCounterQueue() {
            this.count = 0;
            this.queue = new ArrayDeque<>();
        }

        public void hit(int timestamp) {
            if (!queue.isEmpty() && queue.peekLast()[0] == timestamp) {
                queue.peekLast()[1]++;
            } else {
                queue.offer(new int[]{timestamp, 1});
            }
            count++;
            while (!queue.isEmpty() && queue.peek()[0] <= timestamp - 300) {
                count -= queue.poll()[1];
            }
        }

        public int getHits(int timestamp) {
            while (!queue.isEmpty() && queue.peek()[0] <= timestamp - 300) {
                count -= queue.poll()[1];
            }
            return count;
        }
    }

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
}

