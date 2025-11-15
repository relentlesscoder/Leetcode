package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2025.
 * #2748 https://leetcode.com/problems/number-of-beautiful-pairs/
 */
public class NumberOfBeautifulPairs {

    // time O(n * log(MAX)), space O(1)
    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        int[] freq = new int[10];
        for (int num : nums) {
            int x = num % 10;
            for (int y = 1; y <= 9; y++) {
                if (gcd(x, y) == 1) { // O(log(min(x, y)))
                    res += freq[y];
                }
            }
            int d = 0;
            while (num > 0) { // O(log(num))
                d = num % 10;
                num /= 10;
            }
            freq[d]++;
        }
        return res;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
