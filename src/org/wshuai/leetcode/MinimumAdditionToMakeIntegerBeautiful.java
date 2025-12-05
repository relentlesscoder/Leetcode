package org.wshuai.leetcode;

/**
 * Created by Wei on 08/11/2025.
 * #2457 https://leetcode.com/problems/minimum-addition-to-make-integer-beautiful/
 */
public class MinimumAdditionToMakeIntegerBeautiful {

    // time O(log(n) * log(n)), space O(1)
    public long makeIntegerBeautiful(long n, int target) {
        // example: 123456 -> 123460 -> 123500 -> 124000 -> 130000 -> 2000000
        long n0 = n, base = 1;
        while (sum(n) > target) {
            n = n / 10 + 1;
            base *= 10;
        }
        return n * base - n0;
    }

    private int sum(long n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }
}
