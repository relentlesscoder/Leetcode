package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/11/2016.
 * #0267 https://leetcode.com/problems/palindrome-permutation-ii/
 */
public class PalindromePermutationII {
	// time O((n/2)!)
	public List<String> generatePalindromes(String s) {
		List<String> res = new ArrayList<>();
		if(s == null || s.length() == 0){
			return res;
		}
		int n = s.length() / 2, odd = 0;
		String mid = "";
		char[] arr = new char[n];
		int[] count = new int[256];
		for(char c : s.toCharArray()){
			count[c]++;
		}
		for(int i = 0, j = 0; i < 256; i++){
			int cnt = count[i];
			if(cnt == 0){
				continue;
			}
			char c =  (char)i;
			if(cnt % 2 == 0){
				int k = cnt / 2;
				while(k-- > 0){
					arr[j++] = c;
				}
			}else if(odd == 1){
				return res;
			}else{
				mid = Character.toString(c);
				int k = (cnt - 1) / 2;
				while(k-- > 0){
					arr[j++] = c;
				}
				odd++;
			}
		}
		dfs(0, arr, new boolean[n], new char[n], res, mid);
		return res;
	}

	private void dfs(int start, char[] arr, boolean[] used, char[] cur, List<String> res, String mid){
		if(start == arr.length){
			String val = new String(cur);
			res.add(val + mid + new StringBuilder(val).reverse().toString());
			return;
		}
		for(int i = 0; i < arr.length; i++){
			if(used[i]){
				continue;
			}
			if(i > 0 && arr[i] == arr[i - 1] && !used[i - 1]){
				continue;
			}
			used[i] = true;
			char prev = cur[start];
			cur[start] = arr[i];
			dfs(start + 1, arr, used, cur, res, mid);
			cur[start] = prev;
			used[i] = false;
		}
	}
}
