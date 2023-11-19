package org.wshuai.leetcode;

/**
 * Created by Wei on 08/23/2019.
 * #0953 https://leetcode.com/problems/verifying-an-alien-dictionary/
 */
public class VerifyingAnAlienDictionary {

	// time O(n*m)
	public boolean isAlienSorted(String[] words, String order) {
		int[] arr = new int[26];
		for(int i = 0; i < 26; i++){
			arr[order.charAt(i) - 'a'] = i;
		}
		for(int k = 0; k < words.length - 1; k++){
			String w1 = words[k], w2 = words[k + 1];
			for(int i = 0, j = 0; i < w1.length() || j < w2.length(); i++, j++){
				int o1 = i < w1.length() ? arr[w1.charAt(i) - 'a'] : -1;
				int o2 = j < w2.length() ? arr[w2.charAt(j) - 'a'] : -1;
				if(o1 < o2){
					break;
				}
				if(o1 > o2){
					return false;
				}
			}
		}
		return true;
	}
}
