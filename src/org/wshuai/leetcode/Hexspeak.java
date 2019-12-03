package org.wshuai.leetcode;

/**
 * Created by Wei on 12/2/19.
 * #1271 https://leetcode.com/problems/hexspeak/
 */
public class Hexspeak {
	public String toHexspeak(String num) {
		long val = Long.parseLong(num);
		StringBuilder sb = new StringBuilder();
		while(val > 0){
			long rem = val % 16;
			if(rem > 1L && rem < 10L){
				return "ERROR";
			}
			sb.append(convertToHex((int)rem));
			val /= 16;
		}
		return sb.reverse().toString();
	}

	private String convertToHex(int val){
		if(val == 0){
			return "O";
		}
		if(val == 1){
			return "I";
		}
		if(val == 10){
			return "A";
		}
		if(val == 11){
			return "B";
		}
		if(val == 12){
			return "C";
		}
		if(val == 13){
			return "D";
		}
		if(val == 14){
			return "E";
		}
		return "F";
	}
}
