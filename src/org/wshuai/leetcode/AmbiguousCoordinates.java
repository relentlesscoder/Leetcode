package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2019.
 * #0816 https://leetcode.com/problems/ambiguous-coordinates/
 */
public class AmbiguousCoordinates {
	// time O(n^3), space O(n^2)
	public List<String> ambiguousCoordinates(String S) {
		S = S.substring(1, S.length() - 1);
		List<String> res = new ArrayList<>();
		for(int i = 1; i < S.length(); i++){
			String s1 = S.substring(0, i), s2 = S.substring(i);
			List<String> l1 = parse(s1), l2 = parse(s2);
			if(l1.size() > 0 && l2.size() > 0){
				for(String v1 : l1){
					for(String v2 : l2){
						res.add("(" + v1 + ", " + v2 + ")");
					}
				}
			}
		}
		return res;
	}

	private List<String> parse(String s){
		int n = s.length();
		List<String> result = new ArrayList<>();
		// "0xxxx0" invalid unless a single "0"
		if (s.charAt(0) == '0' && s.charAt(n - 1) == '0') {
			if (n == 1) {
				result.add("0");
			}
			return result;
		}
		// "0xxxxx" the only valid result is "0.xxxxx"
		if (s.charAt(0) == '0') {
			result.add("0." + s.substring(1));
			return result;
		}
		// "xxxxx0" the only valid result is itself
		if (s.charAt(n - 1) == '0') {
			result.add(s);
			return result;
		}
		// add itself
		result.add(s);
		// "xxxx" -> "x.xxx", "xx.xx", "xxx.x"
		for (int i = 1; i < n; i++) {
			result.add(s.substring(0, i) + '.' + s.substring(i));
		}
		return result;
	}
}
