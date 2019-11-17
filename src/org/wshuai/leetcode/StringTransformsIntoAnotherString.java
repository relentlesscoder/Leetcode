package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by Wei on 11/16/2019.
 * #1153 https://leetcode.com/problems/string-transforms-into-another-string/
 */
public class StringTransformsIntoAnotherString {
	public boolean canConvert(String str1, String str2) {
		if (str1.equals(str2)) {
			return true;
		}
		Map<Character, Character> map = new HashMap<>();
		for(int i = 0; i < str1.length(); i++){
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			if(map.containsKey(c1)){
				if(c2 != map.get(c1)){
					return false;
				}
			}else{
				map.put(c1, c2);
			}
		}
		return new HashSet<Character>(map.values()).size() < 26;
	}
}
