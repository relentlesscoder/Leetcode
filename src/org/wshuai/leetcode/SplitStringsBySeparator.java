package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/29/2023.
 * #2788 https://leetcode.com/problems/split-strings-by-separator/
 */
public class SplitStringsBySeparator {

    // time O(n), space O(n)
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (c == separator) {
                    if (sb.length() > 0) {
                        res.add(sb.toString());
                        sb = new StringBuilder();
                    }
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() > 0) {
                res.add(sb.toString());
            }
        }
        return res;
    }
}
