package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/04/2023.
 * #2273 https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/description/
 */
public class FindResultantArrayAfterRemovingAnagrams {

    // time O(n), space O(n)
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> res = new ArrayList<>();
        res.add(words[0]);
        for (int i = 1; i < n; i++) {
            if (!isAnagram(res.get(res.size() - 1), words[i])) {
                res.add(words[i]);
            }
        }
        return res;
    }

    private boolean isAnagram(String w1, String w2) {
        int m = w1.length(), n = w2.length();
        if (m != n) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < m; i++) {
            freq[w1.charAt(i) - 'a']++;
            freq[w2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
