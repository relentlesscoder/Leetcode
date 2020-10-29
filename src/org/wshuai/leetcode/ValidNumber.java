package org.wshuai.leetcode;

/**
 * Created by Wei on 10/16/2016.
 * #0065 https://leetcode.com/problems/valid-number/
 */
public class ValidNumber {

	// time O(n)
	// https://leetcode.com/problems/valid-number/discuss/23738/Clear-Java-solution-with-ifs
	public boolean isNumber(String s) {
		s = s.trim();
		boolean numberSeen = false, eSeen = false, pointSeen = false;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(Character.isDigit(c)){
				numberSeen = true;
			}else if(c == '.'){
				if(pointSeen || eSeen){
					return false;
				}
				pointSeen = true;
			}else if(c == 'e'){
				if(eSeen || !numberSeen){
					return false;
				}
				eSeen = true;
				numberSeen = false; // check numbers after "e"
			}else if(c == '+' || c == '-'){
				if(i != 0 && s.charAt(i - 1) != 'e'){
					return false;
				}
			}else{
				return false;
			}
		}
		return numberSeen;
	}
}
