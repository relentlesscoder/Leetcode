package org.wshuai.leetcode;

/**
 * Created by Wei on 11/21/16.
 * #471 https://leetcode.com/problems/encode-string-with-shortest-length/
 */
public class EncodeStringWithShortestLength {
	public String encode(String s) {
		int N = s.length();
		String[][] dp = new String[N][N];

		for(int l = 0; l < N; l++){
			for(int i = 0; i < N - l; i++){
				int j = i + l;
				String substr = s.substring(i, j + 1);
				dp[i][j] = substr;

				if(j - i >= 4){
					for(int k = i; k < j; k++){
						String val = dp[i][k] + dp[k + 1][j];
						if(val.length() < dp[i][j].length()){
							dp[i][j] = val;
						}
					}

					String pattern = kmp (substr);
					if(pattern.length() == substr.length()) continue; // no repeat pattern found
					String patternEncode = substr.length() / pattern.length() + "[" + dp[i][i + pattern.length() - 1] + "]";
					if(patternEncode.length() < dp[i][j].length()) {
						dp[i][j] = patternEncode;
					}
				}
			}
		}
		return dp[0][s.length() - 1];
	}

	private String kmp (String s) {
		int len = s.length();
		int[] LPS = new int[len];

		int i = 1, j = 0;
		LPS[0] = 0;
		while(i < len) {
			if(s.charAt(i) == s.charAt(j)) {
				LPS[i ++] = ++ j;
			} else if(j == 0) {
				LPS[i ++] = 0;
			} else {
				j = LPS[j - 1];
			}
		}

		int patternLen = len - LPS[len - 1];
		if(patternLen != len && len % patternLen == 0) {
			return s.substring(0, patternLen);
		} else {
			return s;
		}
	}
}
