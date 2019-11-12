package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2019.
 * #816 https://leetcode.com/problems/ambiguous-coordinates/
 */
public class AmbiguousCoordinates {
	public List<String> ambiguousCoordinates(String S) {
		S = S.substring(1, S.length() - 1);
		List<String> set = new ArrayList<>();
		for(int i = 1; i < S.length(); i++){
			String s1 = S.substring(0, i);
			String s2 = S.substring(i);
			List<String> ss1 = parse(s1);
			List<String> ss2 = parse(s2);
			if(ss1.size() > 0 && ss2.size() > 0){
				for(String v1 : ss1){
					for(String v2 : ss2){
						set.add("(" + v1 + ", " + v2 + ")");
					}
				}
			}
		}
		return set;
	}

	private List<String> parse(String S){
		int len = S.length();
		List<String> res = new ArrayList<>();
		// "00012300"
		if(S.startsWith("00") && S.charAt(len - 1) == '0'){
			return res;
		}
		// "00", "010"
		if(len > 1 && S.charAt(0) == '0' && S.charAt(len - 1) == '0'){
			return res;
		}
		// not "012"
		if(!(S.charAt(0) == '0' && len > 1)){
			res.add(S);
		}
		// "0", "1"
		if(len == 1 || S.charAt(len - 1) == '0'){
			return res;
		}
		if(S.charAt(0) == '0'){
			res.add("0." + S.substring(1));
			return res;
		}
		for(int i = 1; i < len; i++){
			res.add(S.substring(0, i) + "." + S.substring(i));
		}
		return res;
	}
}
