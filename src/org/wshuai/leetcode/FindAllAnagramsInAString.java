package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2016.
 * #0438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInAString {

    // time O(n), space O(1)
    public List<Integer> findAnagramsFixedLengthSlidingWindow(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = p.length(), n = s.length();
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']--;
            if (i - m + 1 < 0) {
                continue;
            }
            boolean match = true;
            for (int f : freq) {
                if (f != 0) {
                    match = false;
                    break;
                }
            }
            if (match) {
                res.add(i - m + 1);
            }
            freq[s.charAt(i - m + 1) - 'a']++;
        }
        return res;
    }

    // time O(n), space O(1)
    public List<Integer> findAnagramsSlidingWindow(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int m = p.length(), n = s.length();
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int i = 0, j = 0; j < n; j++) {
            int index = s.charAt(j) - 'a';
            freq[index]--;
            // If there is any character mismatch in current sliding
            // window, this loop advances i to j to invalidate the
            // current window.
            while (freq[index] < 0) {
                ++freq[s.charAt(i++) - 'a'];
            }
            // There is no mismatch for current sliding window and
            // the length is m, meaning a full match is found
            if (j - i + 1 == m) {
                res.add(i);
            }
        }
        return res;
    }
}
