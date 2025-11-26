package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 11/25/2025.
 * #3542 https://leetcode.com/problems/minimum-operations-to-convert-all-elements-to-zero/
 */
public class MinimumOperationsToConvertAllElementsToZero {

    // time O(n), space O(n)
    public int minOperations(int[] nums) {
        // https://leetcode.cn/problems/minimum-operations-to-convert-all-elements-to-zero/solutions/3673804/cong-fen-zhi-dao-dan-diao-zhan-jian-ji-x-mzbl/
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        // We need to find if greater numbers are surrounded by smaller
        // numbers like [1,2,2,1] so we can remove it using two operations.
        // We can use monotonic stack to pop out all greater numbers for
        // each and see number and check if stack top has the same number
        // e.g. [1,1,2,3,2,1,2,3,3,2]
        for (int num : nums) {
            // Greater number can't be removed in the same operation
            // with lower number so pop them out
            while (!stack.isEmpty() && stack.peek() > num) {
                stack.pop();
            }
            // Note that for num = 0 we still need to pop all numbers out or it
            // will impact following processing
            // e.g. [7,2,0,4,2]
            if (num == 0) {
                continue;
            }
            // After we popped all the greater numbers (if any) now
            // there are two cases:
            //   1. stack top is lower than num - index 3 (3) in above
            //   example, we need a new operation to convert it to 0
            //   2. stack top is the same as num - index 4 (2) in above
            //   example, it can be converted to 0 with previous 2s in
            //   the same operation
            if (stack.isEmpty() || stack.peek() < num) {
                res++;
                stack.push(num);
            }
        }
        return res;
    }
}
