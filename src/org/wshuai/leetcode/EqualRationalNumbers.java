package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/30/2019.
 * #972 https://leetcode.com/problems/equal-rational-numbers/
 */
public class EqualRationalNumbers {
	private List<Double> ratios = Arrays.asList(1.0, 1.0 / 9, 1.0 / 99, 1.0 / 999, 1.0 / 9999);

	// https://leetcode.com/problems/equal-rational-numbers/discuss/214205/Java-Math-explained
	public boolean isRationalEqual(String S, String T) {
		return Math.abs(computeValue(S) - computeValue(T)) < 1e-9;
	}

	private double computeValue(String s){
		if(!s.contains("(")){
			return Double.valueOf(s);
		}else{
			double intNonRepeatingValue = Double.valueOf(s.substring(0, s.indexOf('(')));
			int nonRepeatingLength = s.indexOf('(') - s.indexOf('.') - 1;
			int repeatingLength = s.indexOf(')') - s.indexOf('(') - 1;
			int repeatingValue = Integer.parseInt(s.substring(s.indexOf('(') + 1, s.indexOf(')')));
			return intNonRepeatingValue + repeatingValue * Math.pow(0.1, nonRepeatingLength) * ratios.get(repeatingLength);
		}
	}
}
