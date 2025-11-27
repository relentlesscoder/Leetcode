package org.wshuai.leetcode;

/**
 * Created by Wei on 07/24/2017.
 * #0316 https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {

    // time O(n), space O(n)
    public String removeDuplicateLetters(String s) {
		// https://leetcode.cn/problems/remove-duplicate-letters/solutions/2381483/gen-zhao-wo-guo-yi-bian-shi-li-2ni-jiu-m-zd6u/
        int[] freq = new int[26];
        boolean[] added = new boolean[26];
        char[] cs = s.toCharArray();
        for (char c : cs) {
            freq[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (char c : cs) {
            freq[c - 'a']--;
            if (added[c - 'a']) {
                continue;
            }
            // Use StringBuilder as Monotonic stack
            while (!res.isEmpty() && res.charAt(res.length() - 1) > c
                    && freq[res.charAt(res.length() - 1) - 'a'] > 0) {
                added[res.charAt(res.length() - 1) - 'a'] = false;
                res.deleteCharAt(res.length() - 1);
            }
            res.append(c);
            added[c - 'a'] = true;
        }
        return res.toString();
    }
}
