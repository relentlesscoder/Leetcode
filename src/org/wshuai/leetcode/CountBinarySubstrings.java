package org.wshuai.leetcode;

/**
 * Created by Wei on 10/08/2019.
 * #0696 https://leetcode.com/problems/count-binary-substrings/
 */
public class CountBinarySubstrings {
	// time O(n)
	// https://leetcode.com/problems/count-binary-substrings/discuss/108625/PythonC%2B%2BJava-Easy-and-Concise-with-Explanation
	public int countBinarySubstrings(String s) {
		int res = 0, n = s.length(), pre = 0, cur = 1;
		for(int i = 1; i < n; i++){
			if(s.charAt(i) == s.charAt(i - 1)){
				cur++;
			}else{
				res += Math.min(cur, pre);
				pre = cur;
				cur = 1;
			}
		}
		return res + Math.min(cur, pre);
	}
}
