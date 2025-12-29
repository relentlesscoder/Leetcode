package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0744 https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 */
public class FindSmallestLetterGreaterThanTarget {

    // time O(log(n)), space O(1)
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length, low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (letters[mid] < target + 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low == n ? letters[0] : letters[low];
    }
}
