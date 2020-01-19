package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0076 https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {
	// time O(n), space O(256)
	// https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
	public String minWindow(String s, String t) {
		int[] target = new int[256];
		for(char c : t.toCharArray()){
			target[c]++;
		}
		int j = 0, counter = t.length(), min = Integer.MAX_VALUE, head = 0;
		for(int i = 0; i < s.length(); i++){
			if(target[s.charAt(i)]-- > 0){
				counter--;
			}
			while(counter == 0){
				if(i - j + 1 < min){
					min = i - j + 1;
					head = j;
				}
				if(target[s.charAt(j++)]++ == 0){
					counter++;
				}
			}
		}
		return min == Integer.MAX_VALUE ? "" : s.substring(head, head + min);
	}
}
