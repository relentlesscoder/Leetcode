package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/29/2016.
 * #0394 https://leetcode.com/problems/decode-string/
 */
public class DecodeString {

    // time O(n * k), space O(n)
    public String decodeString(String s) {
        int n = s.length(), k = 0;
        StringBuilder sb = new StringBuilder();
        Deque<Integer> rpts = new ArrayDeque<>();
        Deque<StringBuilder> strs = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                rpts.push(k);
                strs.push(sb);
                sb = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                int r = rpts.pop();
                String v = sb.toString();
                while (r-- > 0) {
                    strs.peek().append(v);
                }
                sb = strs.pop();
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
