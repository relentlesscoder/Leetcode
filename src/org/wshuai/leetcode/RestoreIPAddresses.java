package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/10/2016.
 * #0093 https://leetcode.com/problems/restore-ip-addresses/
 */
public class RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new ArrayList<>();
		if(s == null || s.isEmpty() || s.length() > 12){
			return res;
		}
		dfs(0, s, "", res);
		return res;
	}

	private void dfs(int count, String s, String cur, List<String> res){
		if(count == 3){
			if(isValid(s)){
				res.add(cur + "." + s);
			}
			return;
		}
		for(int i = 1; i <= Math.min(3, s.length()); i++){
			String str = s.substring(0, i);
			if(isValid(str)){
				dfs(count + 1, s.substring(i), cur + (count == 0 ? "" : ".") + str, res);
			}
		}
	}

	private boolean isValid(String s){
		if(s.length() == 0 || (s.length() > 1 && s.charAt(0) == '0')){
			return false;
		}
		int val = Integer.parseInt(s);
		return val >= 0 && val <= 255;
	}
}
