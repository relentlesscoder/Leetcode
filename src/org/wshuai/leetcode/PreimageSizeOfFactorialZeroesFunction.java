package org.wshuai.leetcode;

/**
 * Created by Wei on 12/31/2019.
 * #0793 https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
 */
public class PreimageSizeOfFactorialZeroesFunction {
    // time O(log(K))
    // https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/discuss/117821/Four-binary-search-solutions-based-on-different-ideas
    public int preimageSizeFZF(int K) {
        return (int) (binarySearch(K) - binarySearch(K - 1));
    }

    private long binarySearch(int K) {
        long l = 0, r = 5L * (K + 1);
        while (l <= r) {
            long m = l + (r - l) / 2;
            long k = numOfTrailingZeros(m);
            if (k <= K) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return r;
    }

    private long numOfTrailingZeros(long x) {
        long res = 0;
        for (; x > 0; x /= 5) {
            res += x / 5;
        }
        return res;
    }
}
