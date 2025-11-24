package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/07/2023.
 * #1966 https://leetcode.com/problems/binary-searchable-numbers-in-an-unsorted-array/
 */
public class BinarySearchableNumbersInAnUnsortedArray {

    // time O(n), space O(n)
    public int binarySearchableNumbersMonotonicStack(int[] nums) {
        int max = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        // For every num i to be searchable, it needs to be:
        //   1 the max in [0,i]
        //   2 the min in [i,n-1]
        for (int num : nums) {
            // Pop out numbers that violates rule #2
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            // Ensure the number satisfies rule #1
            if (num > max) {
                stack.push(num);
            }
            // Maintain current max
            max = Math.max(max, num);
        }
        return stack.size();
    }

    // time O(n), space O(n)
    public int binarySearchableNumbers(int[] nums) {
        int res = 0, n = nums.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        boolean[] searchable = new boolean[n];
        // for a value to be searchable, it should be strictly greater than all elements
        // at its left and smaller than all elements at its right
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                searchable[i] = true;
                max = nums[i];
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < min) {
                min = nums[i];
                res += (searchable[i] ? 1 : 0);
            }
        }
        return res;
    }
}
