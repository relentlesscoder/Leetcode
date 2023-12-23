package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/23/2023.
 * #1805 https://leetcode.com/problems/number-of-different-integers-in-a-string/
 */
public class NumberOfDifferentIntegersInAString {

    // time O(n), space O(n)
    public int numDifferentIntegers(String word) {
        word = word + "#";
        Set<String> set = new HashSet<>();
        StringBuilder curr = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                curr.append(c);
            } else {
                if (!curr.isEmpty()) {
                    set.add(trimLeadingZeros(curr));
                }
                curr = new StringBuilder();
            }
        }
        return set.size();
    }

    private String trimLeadingZeros(StringBuilder sb) {
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        return i == sb.length() ? "0" : sb.substring(i);
    }
}
