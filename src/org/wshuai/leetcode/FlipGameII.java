package org.wshuai.leetcode;

import java.util.HashMap;

/**
 * Created by Wei on 11/02/2016.
 * #0294 https://leetcode.com/problems/flip-game-ii/
 */
public class FlipGameII {
	private HashMap<String, Boolean> map;

	// time O(n!)
	// with memorization
	public boolean canWin(String s) {
		if(s == null || s.length() < 2){
			return false;
		}
		map = new HashMap();
		return dfs(s.toCharArray());
	}

	private boolean dfs(char[] arr){
		for(int i = 1; i < arr.length; i++){
			if(arr[i] == '+' && arr[i - 1] == '+'){
				arr[i] = '-';
				arr[i - 1] = '-';
				boolean res;
				String key = new String(arr);
				if(map.containsKey(key)){
					res = map.get(key);
				}else{
					res = dfs(arr);
					map.put(key, res);
				}
				// needs to reset status
				arr[i] = '+';
				arr[i - 1] = '+';
				if(!res){
					return true;
				}
			}
		}
		return false;
	}
}
