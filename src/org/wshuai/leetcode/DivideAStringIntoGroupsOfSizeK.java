package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2138 https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/
 */
public class DivideAStringIntoGroupsOfSizeK {

    // time O(n), space O(n)
    public String[] divideString(String s, int k, char fill) {
        int n = s.length(), m = (n + k - 1) / k;
        String[] res = new String[m];
        for (int i = 0, j = 0; i < s.length() || j < m; i += k, j++) {
            char[] arr = new char[k];
            for (int c = 0; c < k; c++) {
                arr[c] = i + c < s.length() ? s.charAt(i + c) : fill;
            }
            res[j] = new String(arr);
        }
        return res;
    }
}
