package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/10/2025.
 * #3272 https://leetcode.com/problems/find-the-count-of-good-integers/
 */
public class FindTheCountOfGoodIntegers {

    private static final int MAX = 11;
    private static final int[] FACTORIAL = new int[MAX];

    static {
        FACTORIAL[0] = 1;
        for (int i = 1; i < MAX; i++) {
            FACTORIAL[i] = FACTORIAL[i - 1] * i;
        }
    }

    // time O(n * log(n) * 10^m), space O(n * 10^m), m = (n - 1) / 2
    public long countGoodIntegers(int n, int k) {
        long res = 0;
        Set<String> visited = new HashSet<>();
        // Construct half of the palindrome:
        //   1. For even digits like n = 4, half-length of the palindrome
        //     is 2 digits so iterate from 10 to 99
        //   2. For odd digits like n = 5, half-length of the palindrome
        //     is 3 digits so iterate from 100 to 999, note that the code
        //     handles the middle digit using `.reverse().substring(n % 2)`
        int base = (int) Math.pow(10, (n - 1) / 2);
        for (int i = base; i < base * 10; i++) {
            String s = Integer.toString(i);
            s += new StringBuilder(s).reverse().substring(n % 2);
            if (Long.parseLong(s) % k > 0) { // check if the number can be divided by k
                continue;
            }

            // Deduplicate - sorting the digits and hashing
            char[] sortedDigits = s.toCharArray();
            Arrays.sort(sortedDigits);
            if (!visited.add(new String(sortedDigits))) {
                continue;
            }

            // Count digit frequency
            int[] freq = new int[10];
            for (char d : sortedDigits) {
                freq[d - '0']++;
            }
            // Leading zero is not allowed so there are (n - freq[0]) ways
            // to pick the first digits. For the rest digits, the ways to
            // select are [n - 1, n - 2, ... ,1].
            int count = (n - freq[0]) * FACTORIAL[n - 1];
            for (int f : freq) {
                count /= FACTORIAL[f]; // Deduplicate for the same digits
            }
            res += count;
        }
        return res;
    }
}
