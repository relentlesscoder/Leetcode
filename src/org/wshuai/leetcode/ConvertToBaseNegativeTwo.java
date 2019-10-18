package org.wshuai.leetcode;

/**
 * Created by Wei on 10/18/2019.
 * #1017 https://leetcode.com/problems/convert-to-base-2/
 */
public class ConvertToBaseNegativeTwo {
	// https://leetcode.com/problems/convert-to-base-2/discuss/265507/JavaC%2B%2BPython-2-lines-Exactly-Same-as-Base-2
	public String baseNeg2(int N) {
		StringBuilder res = new StringBuilder();
		while(N != 0){
			res.append(N & 1);
			//N = N >> 1;
			N = -(N >> 1);
		}
		return res.length() > 0 ? res.reverse().toString() : "0";
	}
}
