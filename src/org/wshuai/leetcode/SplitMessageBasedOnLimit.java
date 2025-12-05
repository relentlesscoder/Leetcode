package org.wshuai.leetcode;

/**
 * Created by Wei on 11/03/2025.
 * #2468 https://leetcode.com/problems/split-message-based-on-limit/
 */
public class SplitMessageBasedOnLimit {

    // time O(n * log(n)), space O(1)
    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        for (int segment = 1, capacity = 0, tailLength = 0; ; segment++) {
            if (segment < 10) {
                tailLength = 5; // Tail like <7/7> or <9/9>
            } else if (segment < 100) {
                if (segment == 10) {
                    // All tails ends with postfix like <?/10> so each tail has length + 1
                    // thus reduce total capacity by 9
                    capacity -= 9;
                }
                tailLength = 7; // Tail like <99/99>
            } else if (segment < 1000) {
                if (segment == 100) {
                    capacity -= 99;
                }
                tailLength = 9; // Tail like <999/999>
            } else {
                if (segment == 1000) {
                    capacity -= 999;
                }
                tailLength = 11; // Tail like <9999/9999>
            }
            if (tailLength >= limit) {
                return new String[] {};
            }
            capacity += limit - tailLength;
            if (capacity < n) {
                continue;
            }
            // Construct the messages if capacity >= n
            String[] res = new String[segment];
            for (int s = 0, index = 0; s < segment; s++) {
                String tail = "<" + (s + 1) + "/" + segment + ">";
                if (s == segment - 1) { // Last segment
                    res[s] = message.substring(index) + tail;
                } else {
                    int len = limit - tail.length(); // Calculate actual message length
                    res[s] = message.substring(index, index + len) + tail;
                    index += len;
                }
            }
            return res;
        }
    }
}
