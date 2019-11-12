package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/31/2019.
 * #1239 https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

	// using array of 26 to record the character could solve it but will be pretty nasty
	// bit manipulation: https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/414172/C%2B%2BPython-Set-Solution
	public int maxLength(List<String> arr) {
		List<Integer> dp = new ArrayList<>();
		dp.add(0);
		int res = 0;
		for(String s : arr){
			int a = 0;
			int dup = 0;
			for(char c : s.toCharArray()){
				dup |= a & (1 << (c - 'a'));
				a |= 1 << (c - 'a');
			}
			if(dup > 0){
				continue;
			}
			// try all combination to find the max
			// 0
			// 0, 1
			// 0, 1, 2 (0 | 2), 1 | 2 (if no conflicts)
			for(int i = dp.size() - 1; i >= 0; i--){
				if((dp.get(i) & a) > 0){
					continue;
				}
				dp.add(dp.get(i) | a);
				res = Math.max(res, Integer.bitCount(dp.get(i) | a));
			}
		}
		return res;
	}
}
