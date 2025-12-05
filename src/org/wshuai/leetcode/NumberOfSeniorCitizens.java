package org.wshuai.leetcode;

/**
 * Created by Wei on 11/22/2023.
 * #2678 https://leetcode.com/problems/number-of-senior-citizens/
 */
public class NumberOfSeniorCitizens {

    // time O(n), space O(1)
    public int countSeniors(String[] details) {
        int res = 0;
        for (String d : details) {
            if ((d.charAt(11) - '0') * 10 + (d.charAt(12) - '0') > 60) {
                res++;
            }
        }
        return res;
    }
}
