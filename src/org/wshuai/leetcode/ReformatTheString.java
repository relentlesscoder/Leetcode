package org.wshuai.leetcode;

import java.util.Stack;

/**
 * Created by Wei on 04/19/2020.
 * #1417 https://leetcode.com/problems/reformat-the-string/
 */
public class ReformatTheString {
	// time O(n), space O(n)
	public String reformat(String s) {
		int n = s.length(), numsCount = 0, i = -1, charsCount;
		Stack<Character> nums = new Stack<>(), chars = new Stack<>();
		char[] temp = s.toCharArray();
		for(char c : temp){
			if(Character.isDigit(c)){
				numsCount++;
				nums.push(c);
			}else{
				chars.push(c);
			}
		}
		charsCount = n - numsCount;
		if(Math.abs(numsCount - charsCount) > 1){
			return "";
		}
		if(numsCount > charsCount){
			temp[++i] = nums.pop();
		}else if(charsCount > numsCount){
			temp[++i] = chars.pop();
		}
		while(!nums.isEmpty()){
			if(i >= 0 && Character.isDigit(temp[i])){
				temp[++i] = chars.pop();
				temp[++i] = nums.pop();
			}else{
				temp[++i] = nums.pop();
				temp[++i] = chars.pop();
			}
		}
		return new String(temp);
	}
}
