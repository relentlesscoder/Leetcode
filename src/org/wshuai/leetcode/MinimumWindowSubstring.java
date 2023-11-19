package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2016.
 * #0076 https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSubstring {

	// time O(n)
	// https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-
	public String minWindow(String s, String t) {
		int[] target = new int[128];
		for(char c : t.toCharArray()){
			target[c]++;
		}
		int unmatched = t.length(), min = Integer.MAX_VALUE, start = -1;
		for(int i = 0, j = 0; j < s.length(); j++){
			if(target[s.charAt(j)]-- > 0){
				unmatched--;
			}
			while(unmatched == 0){ // try shortening the sliding window from left
				if(j - i + 1 < min){
					min = j - i + 1;
					start = i;
				}
				if(target[s.charAt(i++)]++ == 0){ // find first character makes the current sliding window invalid
					unmatched++;
				}
			}
		}
		return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
	}
}
