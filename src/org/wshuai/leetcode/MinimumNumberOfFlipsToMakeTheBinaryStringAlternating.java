package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/2025.
 * #1888 https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/
 */
public class MinimumNumberOfFlipsToMakeTheBinaryStringAlternating {

    // time O(n), space O(1)
    public int minFlips(String s) {
        // Applying operation 1 equals to extend a length n sliding window
        // once:
        // 0,1,2,3,4,5,6,7,8,9,10,11,12
        // 0,1,0,0,1,0,0,1,1,0
        // x,1,0,0,1,0,0,1,1,0, 0
        // x,x,0,0,1,0,0,1,1,0, 0, 1
        // x,x,x,0,1,0,0,1,1,0, 0, 1, 0

        // 0,1,2,3,4,5,6,7,8,9,10,11,12
        // 0,1,0,0,1,0,0,1,1
        // x,1,0,0,1,0,0,1,1,0
        // x,x,0,0,1,0,0,1,1,0, 1
        // x,x,x,0,1,0,0,1,1,0, 1, 0
        int n = s.length(), res = n, count = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            if (s.charAt(i % n) % 2 != i % 2) {
                count++;
            }
            if (i - n + 1 < 0) {
                continue;
            }
            // We can either change the subarray to 101010 or 010101
            // so we need to get min between the two approaches
            res = Math.min(res, Math.min(count, n - count));
            int left = i - n + 1;
            if (s.charAt(left) % 2 != left % 2) {
                count--;
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public int minFlipsVerbose(String s) {
        int n = s.length(), res = n, count = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int idx = i % n;
            count += i % 2 == 1 && s.charAt(idx) == '1' ? 1 : 0;
            count += i % 2 == 0 && s.charAt(idx) == '0' ? 1 : 0;
            if (i - n + 1 < 0) {
                continue;
            }
            res = Math.min(res, Math.min(count, n - count));
            idx = i - n + 1;
            count -= idx % 2 == 1 && s.charAt(idx) == '1' ? 1 : 0;
            count -= idx % 2 == 0 && s.charAt(idx) == '0' ? 1 : 0;
        }
        return res;
    }
}
