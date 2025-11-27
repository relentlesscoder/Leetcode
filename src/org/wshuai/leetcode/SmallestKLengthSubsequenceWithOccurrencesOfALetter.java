package org.wshuai.leetcode;

/**
 * Created by Wei on 11/28/2025.
 * #2030 https://leetcode.com/problems/smallest-k-length-subsequence-with-occurrences-of-a-letter/
 */
public class SmallestKLengthSubsequenceWithOccurrencesOfALetter {

    // time O(n), space O(n)
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        // Same idea as #0402
        char[] arr = s.toCharArray();
        int n = arr.length, count = 0, repeated = 0;
        for (char c : arr) {
            if (c == letter) {
                count++;
            }
        }
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = arr[i];
            if (c == letter) {
                count--;
            }
            // n - i > k - SL - we can delete from the stack since we have enough characters
            // in [i, n - 1] to refill
            // n - i + SL > k
            while (!stack.isEmpty() && stack.charAt(stack.length() - 1) > c
                    && n - i + stack.length() > k
                    && (stack.charAt(stack.length() - 1) != letter
                    || count > Math.max(repetition - repeated, 0))) {
                if (stack.charAt(stack.length() - 1) == letter) {
                    repeated--;
                }
                stack.deleteCharAt(stack.length() - 1);
            }
            if (c == letter && stack.length() + Math.max(repetition - repeated - 1, 0) < k) {
                repeated++;
                stack.append(c);
            } else if (c != letter && stack.length() + Math.max(repetition - repeated, 0) < k) {
                stack.append(c);
            }
        }
        return stack.toString();
    }
}
