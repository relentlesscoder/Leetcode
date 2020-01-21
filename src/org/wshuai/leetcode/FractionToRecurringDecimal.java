package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/07/2016.
 * #0166 https://leetcode.com/problems/fraction-to-recurring-decimal/
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		if (numerator == 0) {
			return "0";
		}
		StringBuilder res = new StringBuilder();
		// "+" or "-"
		res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
		long num = Math.abs((long)numerator);
		long den = Math.abs((long)denominator);
		// integral part
		res.append(num / den);
		num %= den;
		if (num == 0) {
			return res.toString();
		}
		// fractional part
		res.append(".");
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map.put(num, res.length());
		while (num != 0) {
			num *= 10;
			res.append(num / den);
			num %= den;
			if (map.containsKey(num)) {
				int index = map.get(num);
				res.insert(index, "(");
				res.append(")");
				break;
			}
			else {
				map.put(num, res.length());
			}
		}
		return res.toString();
	}
}
