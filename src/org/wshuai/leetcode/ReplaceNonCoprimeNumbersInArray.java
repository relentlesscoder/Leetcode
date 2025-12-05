package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/21/2025.
 * #2197 https://leetcode.com/problems/replace-non-coprime-numbers-in-array/
 */
public class ReplaceNonCoprimeNumbersInArray {

    // time O(n * log(MAX)), space O(n)
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        for (int num : nums) {
            while (!stack.isEmpty() && gcd(num, stack.getLast()) > 1) {
                num = lcm(num, stack.removeLast());
            }
            stack.add(num);
        }
        return stack;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
