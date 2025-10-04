package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2016.
 * #0229 https://leetcode.com/problems/majority-element-ii/
 */
public class MajorityElementII {

    // tim O(n), space O(1)
	public List<Integer> majorityElement(int[] nums) {
		// https://leetcode.com/problems/majority-element-ii/solutions/63537/My-understanding-of-Boyer-Moore-Majority-Vote/
		List<Integer> res = new ArrayList<>();
		int n = nums.length, votes1 = 0, votes2 = 0, cand1 = -1, cand2 = -1, count1 = 0, count2 = 0;
		for (int num : nums) {
			// note that we need to check equality first since if we check votes equals to zero first then
			// it is possible cand1 and cand2 both point to the same number to `split` the votes
			if (num == cand1) {
				votes1++;
			} else if (num == cand2) {
				votes2++;
			} else if (votes1 == 0) {
				cand1 = num;
				votes1 = 1;
			} else if (votes2 == 0) {
				cand2 = num;
				votes2 = 1;
			} else {
				votes1--;
				votes2--;
			}
		}
		for (int num : nums) {
			if (num == cand1) {
				count1++;
			} else if (num == cand2) {
				count2++;
			}
		}
		if (count1 > n / 3) {
			res.add(cand1);
		}
		if (count2 > n / 3) {
			res.add(cand2);
		}
		return res;
	}
}
