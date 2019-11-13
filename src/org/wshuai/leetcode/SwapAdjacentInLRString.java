package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/19.
 * #777 https://leetcode.com/problems/swap-adjacent-in-lr-string/
 */
public class SwapAdjacentInLRString {
	// https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/217070/Python-using-corresponding-position-
	public boolean canTransform(String start, String end) {
		int n = start.length();
		int i = 0;
		int j = 0;
		while(i < n && j < n){
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
