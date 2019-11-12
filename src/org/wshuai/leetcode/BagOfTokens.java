package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/8/19.
 * #948 https://leetcode.com/problems/bag-of-tokens/
 */
public class BagOfTokens {
	public int bagOfTokensScore(int[] tokens, int P) {
		int res = 0;
		int pts = 0;
		Arrays.sort(tokens);
		int i = 0;
		int j = tokens.length - 1;
		while(i <= j){
			if(P >= tokens[i]){
				P -= tokens[i++];
				pts++;
				res = Math.max(res, pts);
			}else if(pts > 0){
				P += tokens[j--];
				pts--;
			}else{
				break;
			}
		}
		return res;
	}
}
