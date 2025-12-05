package org.wshuai.leetcode;

/**
 * Created by Wei on 04/05/2025.
 * #3174 https://leetcode.com/problems/clear-digits/
 */
public class ClearDigits {

    // time O(n), space O(n)
    public String clearDigits(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), count = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                sb.setLength(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
