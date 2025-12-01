package org.wshuai.leetcode;

/**
 * Created by Wei on 12/01/2025.
 * #2904 https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/
 */
public class ShortestAndLexicographicallySmallestBeautifulString {

    // time O(n ^ 2), space O(n)
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length(), shortest = n + 1, index = -1;
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            count += s.charAt(i) - '0';
            while (count == k) {
                int len = i - j + 1;
                if (len < shortest || (len == shortest && smaller(s, j, index, len))) {
                    index = j;
                    shortest = len;
                }
                count -= s.charAt(j++) - '0';
            }
        }
        return index == -1 ? "" : s.substring(index, index + shortest);
    }

    // time O(n ^ 2), space O(n)
    public String shortestBeautifulSubstring1(String s, int k) {
        int n = s.length(), shortest = n + 1, index = -1;
        for (int i = 0, j = 0, count = 0; i < n; i++) {
            count += s.charAt(i) - '0';
            while (count > k || (count == k && s.charAt(j) == '0')) {
                count -= s.charAt(j++) - '0';
            }
            if (count == k) {
                int len = i - j + 1;
                if (len < shortest || (len == shortest && smaller(s, j, index, len))) {
                    index = j;
                    shortest = len;
                }
            }
        }
        return index == -1 ? "" : s.substring(index, index + shortest);
    }

    private boolean smaller(String s, int i, int j, int len) {
        for (int k = 0; k < len; k++) {
            char c1 = s.charAt(i + k), c2 = s.charAt(j + k);
            if (c1 > c2) {
                return false;
            } else if (c1 < c2) {
                return true;
            }
        }
        return false;
    }
}
