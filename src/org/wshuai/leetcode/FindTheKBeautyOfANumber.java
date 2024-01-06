package org.wshuai.leetcode;

/**
 * Created by Wei on 01/06/2024.
 * #2269 https://leetcode.com/problems/find-the-k-beauty-of-a-number/
 */
public class FindTheKBeautyOfANumber {

    // time O(d^2), space O(d)
    public int divisorSubstrings(int num, int k) {
        int res = 0;
        String str = "" + num;
        for (int i = 0; i + k <= str.length(); i++) {
            int val = Integer.parseInt(str.substring(i, i + k));
            if (val != 0 && num % val == 0) {
                res++;
            }
        }
        return res;
    }
}
