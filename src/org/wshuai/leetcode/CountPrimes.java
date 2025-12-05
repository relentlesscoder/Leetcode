package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #0204 https://leetcode.com/problems/count-primes/
 */
public class CountPrimes {

    // time O(n * log(log(n))), space O(n)
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] nonPrime = new boolean[n + 1];
        nonPrime[0] = nonPrime[1] = true;
        for (int i = 2; i <= n; i++) {
            if (!nonPrime[i]) {
                for (int j = i; j <= n / i; j++) {
                    nonPrime[i * j] = true;
                }
            }
        }
        int res = 0;
        for (int i = 2; i <= n; i++) {
            if (!nonPrime[i]) {
                res++;
            }
        }
        return res;
    }
}
