package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 07/31/2025.
 * #2182 https://leetcode.com/problems/construct-string-with-repeat-limit/
 */
public class ConstructStringWithRepeatLimit {

    // time O(n * log(k)), space O(k)
    public String repeatLimitedString(String s, int repeatLimit) {
        PriorityQueue<int[]> maxQueue =
                new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[] freq = new int[26];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                maxQueue.offer(new int[]{i, freq[i]});
            }
        }
        while (!maxQueue.isEmpty()) {
            int[] curr = maxQueue.poll();
            char val = (char)('a' + curr[0]);
            int count = Math.min(curr[1], repeatLimit);
            curr[1] -= count;
            while (count-- > 0) {
                sb.append(val);
            }
            if (curr[1] > 0 && !maxQueue.isEmpty()) {
                int[] next = maxQueue.poll();
                char nextVal = (char)('a' + next[0]);
                next[1]--; // use only one of the next largest character
                sb.append(nextVal);
                if (next[1] > 0) {
                    maxQueue.offer(next);
                }
                maxQueue.offer(curr);
            }
        }
        return sb.toString();
    }
}
