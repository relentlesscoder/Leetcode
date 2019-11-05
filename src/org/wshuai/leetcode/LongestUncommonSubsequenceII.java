package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/2019.
 * #522 https://leetcode.com/problems/longest-uncommon-subsequence-ii/
 */
public class LongestUncommonSubsequenceII {

	// bad question
	public int findLUSlength(String[] strs) {
		int res = -1;
		for(int i = 0; i < strs.length; i++){
			if(strs[i].length() <= res){
				continue;
			}
			int j = -1;
			while(++j < strs.length){
				if(i != j && isSubseq(strs[i], strs[j])){
					break;
				}
			}
			if(j == strs.length){
				res = Math.max(res, strs[i].length());
			}
		}
		return res;
	}

	private boolean isSubseq(String s, String t){
		if(s.length() > t.length()){
			return false;
		}
		if(s.equals(t)){
			return true;
		}
		int i = 0;
		int j = 0;
		while(i < s.length() && j < t.length()){
			if(s.charAt(i) == t.charAt(j)){
				i++;
				j++;
			}else{
				j++;
			}
		}
		return i == s.length();
	}
}
