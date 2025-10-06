package org.wshuai.leetcode;

/**
 * Created by Wei on 10/07/2025.
 * #3483 https://leetcode.com/problems/unique-3-digit-even-numbers/
 */
public class Unique3DigitEvenNumbers {

    // time O(n), space O(1)
    public int totalNumbers(int[] digits) {
        // https://leetcode.com/problems/unique-3-digit-even-numbers/solutions/6540826/mathematical-solution-permutation-detailed-explanation-c-java-100-beat-runtime-memory/?envType=company&envId=google&favoriteSlug=google-all
        int even = 0, zero = 0, all = 0;
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }
        for (int i = 0; i < 10; i++) {
            if (freq[i] > 0) {
                if (i == 0) {
                    zero++;
                }
                if (i % 2 == 0) {
                    even++;
                }
                all++;
            }
        }

        // all 3 digits are unique
        int count = even * (all - 1) * (all - 2);
        if (zero == 1) {
            count -= (even - 1) * (all - 2);
        }

        // 2 same digits and 1 unique
        for (int i = 0; i < 10; i++) {
            if (freq[i] >= 2) {
                if (i == 0) {
                    count += all - 1;
                } else if (i % 2 == 1) {
                    count += even;
                } else {
                    count += 3 * (even - 1) - zero;
                    count += 2 * (all - even);
                }
            }
        }

        // all 3 digits are same
        for (int i = 2; i < 10; i += 2) {
            if (freq[i] >= 3) {
                count++;
            }
        }
        return count;
    }
}
