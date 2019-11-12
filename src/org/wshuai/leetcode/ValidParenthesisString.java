package org.wshuai.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/12/19.
 * #678 https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidParenthesisString {

	// very smart solution - https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
	public boolean checkValidString(String s) {
		int low = 0;
		int high = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '('){
				low++;
				high++;
			}else if(s.charAt(i) == ')'){
				if(low > 0){
					low--;
				}
				high--;
			}else{
				if(low > 0){
					low--;
				}
				high++;
			}
			if(high < 0){
				return false;
			}
		}
		return low == 0;
	}

	Map<String, Boolean> map;

	public boolean checkValidStringDfsWithMemo(String s) {
		map = new HashMap<>();
		return dfs(0, 0, s);
	}

	private boolean dfs(int s, int cur, String str){
		if(cur < 0){
			return false;
		}
		if(s == str.length() && cur == 0){
			return true;
		}
		String key = s + "|" + cur;
		if(map.containsKey(key)){
			return map.get(key);
		}
		int score = cur;
		for(int i = s; i < str.length(); i++){
			if(str.charAt(i) == '('){
				score++;
			}else if(str.charAt(i) == ')'){
				score--;
				if(score < 0){
					return false;
				}
			}else{
				for(int j = -1; j <= 1; j++){
					if(dfs(i + 1, score + j, str)){
						return true;
					}
				}
			}
		}
		map.put(key, score == 0);
		return map.get(key);
	}
}
