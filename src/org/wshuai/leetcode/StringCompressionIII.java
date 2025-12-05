package org.wshuai.leetcode;

/**
 * Created by Wei on 07/31/2025.
 * #3163 https://leetcode.com/problems/string-compression-iii/
 */
public class StringCompressionIII {

    // time O(n), space O(n)
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < word.length()) {
            char curr = word.charAt(index);
            int count = 0;
            while (index < word.length()
                    && count < 9
                    && word.charAt(index) == curr) {
                index++;
                count++;
            }
            sb.append(count).append(curr);
        }
        return sb.toString();
    }
}
