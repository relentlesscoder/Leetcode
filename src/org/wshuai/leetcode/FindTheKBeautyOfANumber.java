package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #2269 https://leetcode.com/problems/find-the-k-beauty-of-a-number/
 */
public class FindTheKBeautyOfANumber {

    // time O(n), space O(n)
    public int divisorSubstringsFixedLengthSlidingWindow(int num, int k) {
        String s = String.valueOf(num);
        int res = 0, n = s.length(), val = 0, pow = (int) Math.pow(10, k - 1);
        for (int i = 0; i < n; i++) {
            val = val * 10 + (s.charAt(i) - '0');
            if (i - k + 1 < 0) {
                continue;
            }
            if (val != 0 && num % val == 0) {
                res++;
            }
            val -= pow * (s.charAt(i - k + 1) - '0');
        }
        return res;
    }

    // time O(n * k), space O(n)
    public int divisorSubstrings(int num, int k) {
        int res = 0;
        String s = String.valueOf(num); // O(n)
        for (int i = 0; i + k <= s.length(); i++) { // O(n)
            int val = Integer.parseInt(s.substring(i, i + k)); // O(k)
            if (val != 0 && num % val == 0) {
                res++;
            }
        }
        return res;
    }
}
