package org.wshuai.leetcode;

/**
 * Created by Wei on 07/01/2025.
 * #2566 https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
 */
public class MaximumDifferenceByRemappingADigit {

    // time O(n), space O(n)
    public int minMaxDifference(int num) {
        String max = Integer.toString(num);
        String min = max;
        int index = 0;
        // replace the first non 9 digit to 9
        while (index < max.length() && max.charAt(index) == '9') {
            index++;
        }
        if (index < max.length()) {
            max = max.replace(max.charAt(index), '9');
        }
        // replace the first digit to 0
        min = min.replace(min.charAt(0), '0');
        return Integer.parseInt(max) - Integer.parseInt(min);
    }
}
