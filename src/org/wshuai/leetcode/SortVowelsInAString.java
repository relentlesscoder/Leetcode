package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/2023.
 * #2785 https://leetcode.com/problems/sort-vowels-in-a-string/
 */
public class SortVowelsInAString {

    // time O(n), space O(1)
    public String sortVowels(String s) {
        int[] map = new int[128];
        char[] arr = s.toCharArray();
        for (char c : arr) {
            if (isVowel(c)) {
                map[c]++;
            }
        }
        for (int i = 0, j = 0; i < arr.length; ) {
            if (!isVowel(arr[i])) {
                i++;
            } else if (map[j]-- > 0) {
                arr[i] = (char) j;
                i++;
            } else {
                j++;
            }
        }
        return new String(arr);
    }

    private boolean isVowel(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
