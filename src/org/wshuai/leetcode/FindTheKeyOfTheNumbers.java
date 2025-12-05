package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3270 https://leetcode.com/problems/find-the-key-of-the-numbers/
 */
public class FindTheKeyOfTheNumbers {

    // time O(1), space O(1)
    public int generateKey(int num1, int num2, int num3) {
        return Math.min(num1 % 10, Math.min(num2 % 10, num3 % 10)) +
                Math.min((num1 / 10) % 10, Math.min((num2 / 10) % 10, (num3 / 10) % 10)) * 10 +
                Math.min((num1 / 100) % 10, Math.min((num2 / 100) % 10, (num3 / 100) % 10)) * 100 +
                Math.min((num1 / 1000) % 10, Math.min((num2 / 1000) % 10, (num3 / 1000) % 10)) * 1000;
    }
}
