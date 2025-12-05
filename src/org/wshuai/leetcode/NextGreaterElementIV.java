package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Created by Wei on 11/24/2025.
 * #2454 https://leetcode.com/problems/next-greater-element-iv/
 */
public class NextGreaterElementIV {

    // time O(n), space O(n)
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        // Stores indexes haven't seen any greater number
        Deque<Integer> s0 = new ArrayDeque<>();
        // Stores indexes have seen 1 greater number (popped out from s0)
        Deque<Integer> s1 = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // Indexes in s1 now sees the second greater number
            while (!s1.isEmpty() && nums[s1.peek()] < nums[i]) {
                res[s1.pop()] = nums[i];
            }
            // Move those indexes in s0 saw the first greater number
            // to s1, note that we need to maintain the original
            // order in s0
            List<Integer> temp = new ArrayList<>();
            while (!s0.isEmpty() && nums[s0.peek()] < nums[i]) {
                temp.add(s0.pop());
            }
            for (int j = temp.size() - 1; j >= 0; j--) {
                s1.push(temp.get(j));
            }
            s0.push(i);
        }
        return res;
    }
}
