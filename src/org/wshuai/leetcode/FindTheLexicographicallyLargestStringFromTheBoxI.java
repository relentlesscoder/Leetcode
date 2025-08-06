package org.wshuai.leetcode;

/**
 * Created by Wei on 08/05/2025.
 * #3403 https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/
 */
public class FindTheLexicographicallyLargestStringFromTheBoxI {

    // time O(n), space O(n)
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        // #1163 https://leetcode.com/problems/last-substring-in-lexicographical-order/
        int i = 0, j = 1, k = 0, n = word.length();
        char[] arr = word.toCharArray();
        while (j + k < n) {
            if (arr[i + k] == arr[j + k]) {
                k += 1;
                continue;
            } else if (arr[i + k] > arr[j + k]) {
                j = j + k + 1;
            } else {
                i = i + k + 1;
            }
            if (i == j) {
                j = j + 1;
            }
            k = 0;
        }
        return word.substring(i, Math.min(n, i + n - numFriends + 1));
    }
}
