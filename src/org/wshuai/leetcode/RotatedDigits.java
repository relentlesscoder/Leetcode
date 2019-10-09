package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 10/8/19.
 * #788 https://leetcode.com/problems/rotated-digits/
 */
public class RotatedDigits {
	private Set<Character> invalid;

	public RotatedDigits(){
		invalid = new HashSet<>();
		invalid.add('3');
		invalid.add('4');
		invalid.add('7');
	}

	public int rotatedDigits(int N) {
		int count = 0;
		for(int i = 1; i <= N; i++){
			if(isGoodNumber(i)){
				count++;
			}
		}
		return count;
	}

	private boolean isGoodNumber(int n){
		char[] count = new char[10];
		char[] arr = ("" + n).toCharArray();
		for(int i = 0; i < arr.length; i++){
			if(invalid.contains(arr[i])){
				return false;
			}
			count[arr[i] - '0']++;
		}
		if(count[2] == 0 && count[5] == 0
			&& count[6] == 0 && count[9] == 0){
			return false;
		}
		return true;
	}
}
