package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Wei on 02/21/2017.
 * #0496 https://leetcode.com/problems/next-greater-element-i/
 */
public class NextGreaterElementI {
	// time O(m+n), space O(m)
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < nums2.length; i++){
			while(!stack.isEmpty() && stack.peek() < nums2[i]){
				map.put(stack.pop(), nums2[i]);
			}
			stack.push(nums2[i]);
		}
		for(int i = 0; i < nums1.length; i++){
			nums1[i] = map.getOrDefault(nums1[i], -1);
		}
		return nums1;
	}
}
