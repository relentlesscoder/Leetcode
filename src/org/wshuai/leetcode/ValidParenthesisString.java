package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0678 https://leetcode.com/problems/valid-parenthesis-string/
 */
public class ValidParenthesisString {

	// time O(n)
	// https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
	public boolean checkValidString(String s) {
		int low = 0, high = 0;
		for(char c : s.toCharArray()){
			if(c == '('){
				low++;
				high++;
			}else if(c == ')'){
				low -= low > 0 ? 1 : 0;
				if(high-- == 0){
					return false;
				}
			}else{
				low -= low > 0 ? 1 : 0;
				high++;
			}
		}
		return low == 0;
	}

	// time O(n^2), space O(n^2)
	public boolean checkValidStringDP(String s) {
		return dfs(0, 0, s.toCharArray(), new Boolean[s.length()][s.length()]);
	}

	private boolean dfs(int start, int open, char[] arr, Boolean[][] dp){
		if(start == arr.length){
			return open == 0;
		}
		if(dp[start][open] != null){
			return dp[start][open];
		}
		if(arr[start] == '('){
			dp[start][open] = dfs(start + 1, open + 1, arr, dp);
		}else if(arr[start] == ')'){
			dp[start][open] = open != 0 && dfs(start + 1, open - 1, arr, dp);
		}else{
			dp[start][open] = dfs(start + 1, open + 1, arr, dp) // '*' as '('
					|| dfs(start + 1, open, arr, dp) // '*' as ''
					|| (open != 0 && dfs(start + 1, open - 1, arr, dp)); // '*' as ')'
		}
		return dp[start][open];
	}
}
