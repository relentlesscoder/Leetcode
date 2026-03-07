package org.wshuai.leetcode.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 09/27/2016.
 * #0379 https://leetcode.com/problems/design-phone-directory/
 */
public class DesignPhoneDirectory {

	// time O(n), space O(MAX)
    private static class PhoneDirectory {

        private final Deque<Integer> queue;
        private final boolean[] used;

        public PhoneDirectory(int maxNumbers) {
            used = new boolean[maxNumbers];
            queue = new ArrayDeque<>();
            for (int i = 0; i < maxNumbers; i++) {
                queue.offer(i);
            }
        }

        public int get() {
            if (queue.isEmpty()) {
                return -1;
            }
            used[queue.peek()] = true;
            return queue.poll();
        }

        public boolean check(int number) {
            return !used[number];
        }

        public void release(int number) {
            if (!used[number]) {
                return;
            }
            queue.offer(number);
            used[number] = false;
        }
    }

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
}
