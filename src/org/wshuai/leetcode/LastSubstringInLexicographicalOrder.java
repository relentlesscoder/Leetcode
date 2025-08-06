package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #1163 https://leetcode.com/problems/last-substring-in-lexicographical-order/
 */
public class LastSubstringInLexicographicalOrder {

    // time O(n), space O(n)
    public String lastSubstring(String s) {
		/*
		The problem is essentially finding the optimal starting point start, such that s[start:]
		is the largest substring. (not s[start : end] because s[start:] >= s[start : end])
		Think of two sequences matching k characters so far and only differ at s[i+k] > s[j+k]
			i ... i+k
			j ... j+k
		Regardless of j relative position to i, we could set j to j+k+1. This is because for any
		j2 (j<j2<j+k); and i2 (i<i2<j+k), i+k-i2 = j+k-j2, substring [j2, j+k] is still smaller
		than [i2, i+k] (these two still only differ at s[i+k] > s[j+k]), so j<j2<j+k can't be the
		optimal starting point.
		Reversely and similarly, if s[i+k] < s[j+k], then set i to i+k+1 at last to break tie,
		when i==j, set j=j+1;
		* */
        int i = 0, j = 1, k = 0;
        int n = s.length();
        char[] arr = s.toCharArray();
        while (j + k < n) {
            if (arr[i + k] == arr[j + k]) {
                k += 1;
                continue;
            } else if (arr[i + k] > arr[j + k]) {
                j = j + k + 1;
            } else {
                i = i + k + 1;
            }
            if (i == j) {
                j = j + 1;
            }
            k = 0;
        }
        return s.substring(i);
    }
}
