package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0777 https://leetcode.com/problems/swap-adjacent-in-lr-string/
 */
public class SwapAdjacentInLRString {
	// time O(n)
	public boolean canTransform(String start, String end) {
		int n = start.length(), i = 0, j = 0;
		// #1 essential observance
		// R can only move to right and L can only move to left so
		// they cannot pass each other, which means the relative position
		// of L and R needs to be the same for them to be transformable
		while(i < n || j < n){
			while(i < n && start.charAt(i) == 'X'){
				i++;
			}
			while(j < n && end.charAt(j) == 'X'){
				j++;
			}
			if(i == n && j == n){
				return true;
			}
			if(i == n || j == n){
				return false;
			}
			if(start.charAt(i) != end.charAt(j)){
				return false;
			}
			// #2 important check
			// if the current character in start is 'R', then i should be less than or equal to
			// j since R can only move to the right. For the case of 'L', it is the opposite.
			if((start.charAt(i) == 'R' && i <= j) || (start.charAt(i) == 'L' && i >= j)){
				i++;
				j++;
			}else{
				return false;
			}
		}
		return true;
	}
}
