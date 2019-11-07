package org.wshuai.leetcode;

/**
 * Created by Wei on 11/6/2019.
 * #838 https://leetcode.com/problems/push-dominoes/
 */
public class PushDominoes {
	public String pushDominoes(String dominoes) {
		String d = 'L' + dominoes + 'R';
		StringBuilder res = new StringBuilder();
		for (int i = 0, j = 1; j < d.length(); j++) {
			if (d.charAt(j) == '.'){
				continue;
			}
			// only rewrite the middle
			int middle = j - i - 1;
			// i > 0 exclude the prefix
			if (i > 0){
				// add the head of the current subarray
				// the postfix will be excluded
				res.append(d.charAt(i));
			}
			// L...L or R...R
			if (d.charAt(i) == d.charAt(j)){
				for (int k = 0; k < middle; k++){
					res.append(d.charAt(i));
				}
			// L...R
			}else if (d.charAt(i) == 'L' && d.charAt(j) == 'R'){
				for (int k = 0; k < middle; k++){
					res.append('.');
				}
			// R...L
			}else {
				for (int k = 0; k < middle / 2; k++){
					res.append('R');
				}
				if (middle % 2 == 1) {
					res.append('.');
				}
				for (int k = 0; k < middle / 2; k++){
					res.append('L');
				}
			}
			i = j;
		}
		return res.toString();
	}
}
