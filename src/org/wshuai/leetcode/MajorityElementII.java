package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/05/2016.
 * #0229 https://leetcode.com/problems/majority-element-ii/
 */
public class MajorityElementII {
	// tim O(n)
	// https://leetcode.com/problems/majority-element-ii/discuss/63537/My-understanding-of-Boyer-Moore-Majority-Vote
	public List<Integer> majorityElement(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if(nums == null || nums.length == 0){
			return res;
		}
		int n = nums.length, t = n / 3, cand1 = 0, cand2 = 0, count1 = 0, count2 = 0;
		for(int i = 0; i < n; i++){
			int num = nums[i];
			if(num == cand1){
				count1++;
			}else if(num == cand2){
				count2++;
			}else if(count1 == 0){
				cand1 = num;
				count1 = 1;
			}else if(count2 == 0){
				cand2 = num;
				count2 = 1;
			}else{
				count1--;
				count2--;
			}
		}
		count1 = 0;
		count2 = 0;
		for(int i : nums){
			if(i == cand1){
				count1++;
			}else if(i == cand2){
				count2++;
			}
		}
		if(count1 > t){
			res.add(cand1);
		}
		if(count2 > t){
			res.add(cand2);
		}
		return res;
	}
}
