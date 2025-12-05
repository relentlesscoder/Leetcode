package org.wshuai.leetcode;

/**
 * Created by Wei on 08/03/2025.
 * #2165 https://leetcode.com/problems/smallest-value-of-the-rearranged-number/
 */
public class SmallestValueOfTheRearrangedNumber {

    // time O(n), space O(1)
    public long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }
        long res = 0, val = num < 0 ? -num : num;
        int[] freq = new int[10];
        while (val > 0) {
            freq[(int)(val % 10)]++;
            val /= 10;
        }
        if (num < 0) {
            for (int i = 9; i >= 0; i--) {
                while (freq[i]-- > 0) {
                    res *= 10;
                    res += i;
                }
            }
            res = -res;
        } else {
            if (freq[0] > 0) { // handles leading 0
                for (int i = 1; i <= 9; i++) {
                    if (freq[i] > 0) {
                        res += i;
                        freq[i]--;
                        break;
                    }
                }
            }
            for (int i = 0; i <= 9; i++) {
                while (freq[i]-- > 0) {
                    res *= 10;
                    res += i;
                }
            }
        }
        return res;
    }
}
