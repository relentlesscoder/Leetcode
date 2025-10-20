package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/10/2025.
 * #2081 https://leetcode.com/problems/sum-of-k-mirror-numbers/
 */
public class SumOfKMirrorNumbers {

    private static final int MAX = 30;
    private static final List<Long>[] ANWSERS = new ArrayList[10];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;

        Arrays.setAll(ANWSERS, i -> new ArrayList<>());
        for (int base = 1; ; base *= 10) {
            // For each i in [base, base * 10), construct odd length palindrome
            // For example for base 10:
            //   10 -> 101
            //   11 -> 111
            //   12 -> 121
            //   ...
            for (int i = base; i < base * 10; i++) {
                long x = i;
                // Start with i/10 since last digit will be used as middle of the
                // palindrome.
                for (int t = i / 10; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
            // For each i in [base, base * 10), construct even length palindrome
            // For example for base 10:
            //   10 -> 1001
            //   11 -> 1111
            //   12 -> 1221
            //   ...
            for (int i = base; i < base * 10; i++) {
                long x = i;
                for (int t = i; t > 0; t /= 10) {
                    x = x * 10 + t % 10;
                }
                if (doPalindrome(x)) {
                    return;
                }
            }
        }
    }

    private boolean doPalindrome(long x) {
        boolean done = true;
        for (int k = 2; k < 10; k++) {
            if (ANWSERS[k].size() < MAX && isKPalindrome(x, k)) {
                ANWSERS[k].add(x);
            }
            if (ANWSERS[k].size() < MAX) {
                done = false;
            }
        }
        if (!done) {
            return false;
        }

        for (int k = 2; k < 10; k++) {
            // 原地求前缀和
            List<Long> s = ANWSERS[k];
            for (int i = 1; i < MAX; i++) {
                s.set(i, s.get(i) + s.get(i - 1));
            }
        }
        return true;
    }

    // 力扣 9. 回文数
    private boolean isKPalindrome(long x, int k) {
        if (x % k == 0) {
            return false;
        }
        int rev = 0;
        while (rev < x / k) {
            rev = rev * k + (int) (x % k);
            x /= k;
        }
        return rev == x || rev == x / k;
    }

    public long kMirror(int k, int n) {
        init();
        return ANWSERS[k].get(n - 1);
    }
}
