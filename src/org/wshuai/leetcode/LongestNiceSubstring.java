package org.wshuai.leetcode;

/**
 * Created by Wei on 02/25/2021.
 * #1763 https://leetcode.com/problems/longest-nice-substring/
 */
public class LongestNiceSubstring {

    // time O(n), space O(1)
    public String longestNiceSubstring(String s) {
        // Same idea as #0395, we find count N of distinct upper+lower pairs
        // from the input and iterate by the occurrence i of distinct pairs
        // from [1,N] and find the longest substring that contains i pairs,
        // e.g. for input "YazaAay" the distinct pairs are [a,A] and [y,Y] so
        // we only need to iterate for 1 and 2.
        String res = "";
        // type is count of distinct [a:A, ..., Z:z] pairs in input
        int n = s.length(), type = 0, longest = 0, index = n;
        int[][] freq = new int[26][2];
        char[] arr = s.toCharArray();
        for (char c : arr) {
            int idx1 = c - (c >= 'a' && c <= 'z' ? 'a' : 'A');
            int idx2 = c >= 'a' && c <= 'z' ? 0 : 1;
            if (freq[idx1][idx2]++ == 0 && freq[idx1][1 - idx2] > 0) {
                type++;
            }
        }
        if (type == 0) { // No upper+lower pairs are found
            return res;
        }
        for (int i = 1; i <= type; i++) { // O(26)
            int[] ans = longestNiceSubstringUnique(arr, i);
            if (ans[0] > longest || (ans[0] == longest && ans[1] < index)) {
                longest = ans[0];
                index = ans[1];
                res = s.substring(ans[1], ans[1] + ans[0]);
            }
        }
        return res;
    }

    private int[] longestNiceSubstringUnique(char[] arr, int k) {
        int n = arr.length;
        int[] res = new int[]{0, n};
        int[][] freq = new int[26][2];
        for (int i = 0, j = 0, distinct = 0, valid = 0; i < n; i++) {
            char c = arr[i];
            int idx1 = c - (c >= 'a' && c <= 'z' ? 'a' : 'A');
            int idx2 = c >= 'a' && c <= 'z' ? 0 : 1;
            if (freq[idx1][idx2] == 0 && freq[idx1][1 - idx2] == 0) {
                distinct++;
            }
            if (freq[idx1][idx2]++ == 0 && freq[idx1][1 - idx2] > 0) {
                valid++;
            }
            while (distinct > k) {
                c = arr[j];
                idx1 = c - (c >= 'a' && c <= 'z' ? 'a' : 'A');
                idx2 = c >= 'a' && c <= 'z' ? 0 : 1;
                if (freq[idx1][idx2] == 1 && freq[idx1][1 - idx2] == 0) {
                    distinct--;
                }
                if (--freq[idx1][idx2] == 0 && freq[idx1][1 - idx2] > 0) {
                    valid--;
                }
                j++;
            }
            if (distinct == valid && i - j + 1 > res[0] || (i - j + 1 == res[0] && j < res[1])) {
                res[0] = i - j + 1;
                res[1] = j;
            }
        }
        return res;
    }
}
