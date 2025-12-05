package org.wshuai.leetcode;

/**
 * Created by Wei on 06/29/2025.
 * #3167 https://leetcode.com/problems/better-compression-of-string/
 */
public class BetterCompressionOfString {

    // time O(n), space O(n)
    public String betterCompression(String compressed) {
        int[] freq = new int[26];
        char[] compressedCharArray = compressed.toCharArray();
        char curr = compressedCharArray[0];
        int count = 0;
        for (int i = 1; i < compressedCharArray.length; i++) {
            char c = compressedCharArray[i];
            if (c >= 'a' && c <= 'z') {
                freq[curr - 'a'] += count;
                curr = c;
                count = 0;
            } else {
                count = count * 10 + (c - '0');
            }
        }
        freq[curr - 'a'] += count;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                res.append((char)('a' + i));
                res.append(freq[i]);
            }
        }
        return res.toString();
    }
}
