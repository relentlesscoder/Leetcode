package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/5/16.
 * #229 https://leetcode.com/problems/majority-element-ii/
 */
public class MajorityElementII {
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> lst = new ArrayList<Integer>();
		if (nums == null || nums.length == 0) {
			return lst;
		}
		int len = nums.length;
		int c1 = 1;
		int c2 = 0;
		int n1 = nums[0];
		int n2 = 0;
		for (int i = 1; i < len; i++) {
			if (nums[i] == n1) {
				c1++;
			} else if (nums[i] == n2) {
				c2++;
			} else if (c1 != 0 && c2 != 0) {
				c1--;
				c2--;
			} else {
				if (c1 == 0) {
					n1 = nums[i];
					c1 = 1;
				} else {
					n2 = nums[i];
					c2 = 1;
				}
			}
		}
		c1 = 0;
		c2 = 0;
		for (int i = 0; i < len; i++) {
			if (nums[i] == n1) {
				c1++;
			} else if (nums[i] == n2) {
				c2++;
			}
		}
		int x = len / 3;
		if (c1 > x) {
			lst.add(n1);
		}
		if (c2 > x) {
			lst.add(n2);
		}
		return lst;
	}
}
