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
        List<String> res = new ArrayList<>();
        for (int i = 0, j; i < words.length; ) {
            res.add(words[i]);
            j = i + 1;
            while (j < words.length && isAnagrams(words[i], words[j])) {
                j++;
            }
            i = j;
        }
        return res;
    }

    private boolean isAnagrams(String w1, String w2) {
        if (w1.length() != w2.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < w1.length(); i++) {
            count[w1.charAt(i) - 'a']++;
            count[w2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
