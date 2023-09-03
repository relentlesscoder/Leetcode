package org.wshuai.leetcode;

/**
 * Created by Wei on 09/03/2023.
 * #1925 https://leetcode.com/problems/count-square-sum-triples/description/
 */
public class CountOperationsToObtainZero {

    // time O(log(m+n)), space O(1)
    public int countOperations(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            return 0;
        }
        return num1/num2 + countOperations(num2, num1%num2);
    }
}
