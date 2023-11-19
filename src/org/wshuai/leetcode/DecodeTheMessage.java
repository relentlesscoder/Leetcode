package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/18/2023.
 * #2325 https://leetcode.com/problems/decode-the-message/
 */
public class DecodeTheMessage {

    // time O(n), space O(1)
    public String decodeMessage(String key, String message) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        for (int i = 0, j = 0; i < key.length() && j < 26; i++) {
            char c = key.charAt(i);
            if (c == ' ' || map[c - 'a'] != -1) {
                continue;
            }
            map[c - 'a'] = j++;
        }
        int index = 0;
        char[] res = new char[message.length()];
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                res[index++] = ' ';
            } else {
                res[index++] = (char) (map[c - 'a'] + 'a');
            }
        }
        return new String(res);
    }
}
