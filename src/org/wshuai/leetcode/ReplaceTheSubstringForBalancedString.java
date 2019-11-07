package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/19.
 * #1234 https://leetcode.com/problems/replace-the-substring-for-balanced-string/
 */
public class ReplaceTheSubstringForBalancedString {
	// great idea - https://leetcode.com/problems/replace-the-substring-for-balanced-string/discuss/408978/JavaC%2B%2BPython-Sliding-Window
	public int balancedString(String s) {
		int[] count = new int[128];
		int N = s.length();
		int res = N;
		int i = 0;
		int k = N / 4;
		for(int j = 0; j < N; j++){
			count[s.charAt(j)]++;
		}
		for(int j = 0; j < N; j++){
			count[s.charAt(j)]--;
			while(i < N
					&& count['Q'] <= k
					&& count['W'] <= k
					&& count['E'] <= k
					&& count['R'] <= k){
				res = Math.min(res, j - i + 1);
				count[s.charAt(i++)]++;
			}
		}
		return res;
	}
}
