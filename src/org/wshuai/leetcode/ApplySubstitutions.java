package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 07/06/2025.
 * #3481 https://leetcode.com/problems/apply-substitutions/
 */
public class ApplySubstitutions {

    // time O(m + n), space O(m)
    public String applySubstitutions(List<List<String>> replacements, String text) {
        Map<String, String> mapping = new HashMap<>();
        for (List<String> replacement : replacements) {
            mapping.put(replacement.get(0), replacement.get(1));
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            if (text.charAt(i) == '%') {
                j = i + 1;
                while (j < text.length() && text.charAt(j) != '%') {
                    j++;
                }
                res.append(replace(text.substring(i + 1, j), mapping));
                i = j;
            } else {
                res.append(text.charAt(i));
            }
        }
        return res.toString();
    }

    private String replace(String key, Map<String, String> mapping) {
        String value = mapping.get(key);
        if (!value.contains("%")) {
            return value;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 0; i < value.length(); i++) {
            if (value.charAt(i) == '%') {
                j = i + 1;
                while (j < value.length() && value.charAt(j) != '%') {
                    j++;
                }
                res.append(replace(value.substring(i + 1, j), mapping));
                i = j;
            } else {
                res.append(value.charAt(i));
            }
        }
        mapping.put(key, res.toString());
        return mapping.get(key);
    }
}
