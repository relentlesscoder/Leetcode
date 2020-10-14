package org.wshuai.leetcode;

/**
 * Created by Wei on 11/11/2019.
 * #0858 https://leetcode.com/problems/mirror-reflection/
 */
public class MirrorReflection {

	// https://leetcode.com/problems/mirror-reflection/discuss/146336/Java-solution-with-an-easy-to-understand-explanation
	public int mirrorReflection(int p, int q) {
		int m = 1, n = 1;
		while(m * p != n * q){
			n++;
			m = n * q / p;
		}
		if (m % 2 == 0 && n % 2 == 1) return 0;
		if (m % 2 == 1 && n % 2 == 1) return 1;
		if (m % 2 == 1 && n % 2 == 0) return 2;
		return -1;
	}
}
