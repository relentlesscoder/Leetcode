package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #2129 https://leetcode.com/problems/capitalize-the-title/
 */
public class CapitalizeTheTitle {

    // time O(n), space O(n)
    public String capitalizeTitle(String title) {
        StringBuilder res = new StringBuilder(), sb = new StringBuilder();
        title += " ";
        for (char c : title.toCharArray()) {
            if (c == ' ') {
                if (!sb.isEmpty()) {
                    res.append(sb.length() <= 2 ? Character.toLowerCase(sb.charAt(0)) : Character.toUpperCase(sb.charAt(0)));
                    for (int i = 1; i < sb.length(); i++) {
                        res.append(Character.toLowerCase(sb.charAt(i)));
                    }
                    res.append(" ");
                }
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        return res.substring(0, res.length() - 1);
    }
}
