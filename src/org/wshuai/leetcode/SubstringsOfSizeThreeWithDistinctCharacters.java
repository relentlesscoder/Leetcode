package org.wshuai.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Wei on 09/03/2023.
 * #1876 https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/
 */
public class SubstringsOfSizeThreeWithDistinctCharacters {

    // time O(n), space O(1)
    public int countGoodSubstrings(String s) {
        int res = 0, i = 0, j = 0;
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);
        for (; j < s.length(); j++) {
            int curr = s.charAt(j) - 'a';
            // maintain index i and j so substring (i, j) does not
            // contain duplicate characters
            // example - "hlezqgllzywhcgzqvaat"
            if (lastIndex[curr] >= i) {
                // if the last occurrence is in the current sliding window,
                // we need to advance the start index to exclude the last occurrence.
                // example "hlh"
                i = lastIndex[curr] + 1;
            }
            if (j - i + 1 >= 3) {
                res++;
            }
            lastIndex[curr] = j;
        }
        return res;
    }
}
