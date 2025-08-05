package org.wshuai.leetcode;

/**
 * Created by Wei on 08/04/2025.
 * #2381 https://leetcode.com/problems/shifting-letters-ii/
 */
public class ShiftingLettersII {

    // time O(n + m), space O(n)
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] arr = new int[n + 1];
        for (int[] shift : shifts) {
            if (shift[2] == 1) {
                arr[shift[0]] += 1;
                arr[shift[1] + 1] -= 1;
            } else {
                arr[shift[0]] -= 1;
                arr[shift[1] + 1] += 1;
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 0; i < n; i++) {
            j = (j + arr[i] % 26 + 26) % 26;
            int val = ((int)(s.charAt(i) - 'a') + j) % 26;
            res.append((char)('a' + val));
        }
        return res.toString();
    }
}
