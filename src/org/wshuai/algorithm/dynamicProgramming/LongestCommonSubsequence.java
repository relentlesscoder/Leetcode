package org.wshuai.algorithm.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestCommonSubsequence {

	private int[][] dp;

	public LongestCommonSubsequence() {
	}

	private int getLCSLength(String A, String B) {

		dp = new int[A.length() + 1][B.length() + 1];
		int r = A.length();
		int c = B.length();

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[r][c];
	}

	public String getLCS(String A, String B){

		dp = new int[A.length() + 1][B.length() + 1];
		int len = getLCSLength(A, B);

		int i = A.length();
		int j = B.length();

		char[] arr = new char[dp[i][j]];
		int k = dp[i][j] - 1;
		while(i > 0 && j > 0){
			if(A.charAt(i - 1) == B.charAt(j - 1)){
				arr[k--] = A.charAt(i - 1);
				i--;
				j--;
			}else if(dp[i - 1][j] >= dp[i][j-1]){
				i--;
			}else{
				j--;
			}
		}
		return new String(arr);
	}

	public List<String> getAllLCS(String A, String B){
		dp = new int[A.length() + 1][B.length() + 1];
		getLCSLength(A, B);

		return getAllLCS(A.length(), B.length(), A, B);
	}

	public List<String> getAllLCS(int i, int j, String A, String B){

		List<String> res = new ArrayList<>();

		if(i == 0 || j == 0){
			res.add("");
			return res;
		}

		Set<String> set = new HashSet<>();
		if(A.charAt(i - 1) == B.charAt(j - 1)){
			List<String> lst = getAllLCS(i - 1, j - 1, A, B);
			for(String s: lst){
				set.add(s + A.charAt(i - 1));
			}
		}else{
			if(dp[i - 1][j] >= dp[i][j - 1]){
				List<String> lst = getAllLCS(i - 1, j, A, B);
				set.addAll(lst);
			}
			if(dp[i - 1][j] <= dp[i][j - 1]){
				List<String> lst = getAllLCS(i, j - 1, A, B);
				set.addAll(lst);
			}
		}

		res.addAll(set);
		return res;
	}
}
