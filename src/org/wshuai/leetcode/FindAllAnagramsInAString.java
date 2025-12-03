package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/29/2016.
 * #0438 https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class FindAllAnagramsInAString {

    // time O(n), space O(1)
    public List<Integer> findAnagramsTrackMismatches(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = 0;
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
            m++;
        }
        for (int i = 0, j = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']--;
            // If there is any mismatch in current sliding window,
            // advances j to invalidate the current window.
            while (freq[s.charAt(i) - 'a'] < 0) {
                freq[s.charAt(j++) - 'a']++;
            }
            // No mismatch found for current sliding window and the
            // window length is m meaning a full match is found
            if (i - j + 1 == m) {
                res.add(j);
            }
        }
        return res;
    }

    // time O(n), space O(1)
    public List<Integer> findAnagramsTrackMatches(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), types = 0;
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            if (freq[c - 'a']++ == 0) {
                types++;
            }
        }
        int[] counter = new int[26];
        for (int i = 0, j = 0, matches = 0; i < n; i++) {
            int idx1 = s.charAt(i) - 'a';
            if (++counter[idx1] == freq[idx1]) {
                matches++;
            }
            while (counter[idx1] > freq[idx1]) {
                int idx2 = s.charAt(j) - 'a';
                if (counter[idx2]-- == freq[idx2]) {
                    matches--;
                }
                j++;
            }
            if (matches == types) {
                res.add(j);
            }
        }
        return res;
    }
}
