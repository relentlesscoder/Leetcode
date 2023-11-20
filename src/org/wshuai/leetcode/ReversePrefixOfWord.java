package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2000 https://leetcode.com/problems/reverse-prefix-of-word/description/
 */
public class ReversePrefixOfWord {

    // time O(n), space O(1)
    public String reversePrefix(String word, char ch) {
        int index = -1;
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            int left = 0, right = index;
            while (left < right) {
                char temp = arr[left];
                arr[left++] = arr[right];
                arr[right--] = temp;
            }
        }
        return new String(arr);
    }
}
