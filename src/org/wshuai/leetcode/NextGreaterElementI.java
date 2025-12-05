package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 02/21/2017.
 * #0496 https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElementI {

    // time O(m + n), space O(m)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[] res = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < m; i++) {
            while (stack.size() > 1 && nums2[stack.peek()] < nums2[i]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }
            stack.push(i);
        }
        for (int i = 0; i < n; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
