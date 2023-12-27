package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/26/2023.
 * #2231 https://leetcode.com/problems/largest-number-after-digit-swaps-by-parity/
 */
public class LargestNumberAfterDigitSwapsByParity {

    // time O(d), space O(d)
    public int largestInteger(int num) {
        PriorityQueue<Integer> evens = new PriorityQueue<>(), odds = new PriorityQueue<>();
        int res = 0, val = num, pos = 1;
        while (val > 0) {
            int digit = val % 10;
            val /= 10;
            if (digit % 2 == 0) {
                evens.offer(digit);
            } else {
                odds.offer(digit);
            }
        }
        val = num;
        while (val > 0) {
            int digit = val % 10;
            val /= 10;
            res += (digit % 2 == 0 ? evens.poll() : odds.poll()) * pos;
            pos *= 10;
        }
        return res;
    }
}
