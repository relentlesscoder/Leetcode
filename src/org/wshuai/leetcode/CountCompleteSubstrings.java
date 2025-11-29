package org.wshuai.leetcode;

/**
 * Created by Wei on 11/29/2025.
 * #2953 https://leetcode.com/problems/count-complete-substrings/
 */
public class CountCompleteSubstrings {

    // time O(26 * n), space O(n)
    public int countCompleteSubstrings(String word, int k) {
        int res = 0, n = word.length(), prev = 0;
        char[] arr = word.toCharArray();
        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) > 2) { // Split the array
                res += count(arr, prev, i - 1, k);
                prev = i;
            }
        }
        res += count(arr, prev, n - 1, k);
        return res;
    }

    private int count(char[] word, int start, int end, int k) {
        int res = 0;
        // It is required that every character occurs exact k times
        // then we just need to iterate the distinct characters
        // the substring could have - x in [1,26]. Then we can use
        // x * k fixed length sliding window to calculate for each.
        for (int x = 1; x <= 26 && x * k <= end - start + 1; x++) {
            int s = x * k, distinct = 0, overloaded = 0;
            int[] freq = new int[26];
            for (int i = start; i <= end; i++) {
                if (freq[word[i] - 'a'] == 0) {
                    distinct++;
                }
                if (freq[word[i] - 'a']++ == k) {
                    overloaded++;
                }
                int left = i - s + 1;
                if (left < start) {
                    continue;
                }
                if (distinct == x && overloaded == 0) {
                    res++;
                }
                if (freq[word[left] - 'a'] == 1) {
                    distinct--;
                }
                if (--freq[word[left] - 'a'] == k) {
                    overloaded--;
                }
            }
        }
        return res;
    }
}
