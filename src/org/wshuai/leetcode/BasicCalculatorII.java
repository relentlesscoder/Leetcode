package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 3/14/2017.
 * #227 https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {
	public int calculate(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		int len = s.length();
		List<String> lst = new ArrayList<String>();
		int i = 0;
		int j = 0;
		while (j < len) {
			char op = s.charAt(j);
			if (j == len - 1 || op == '+' || op == '+' || op == '-' || op == '*' || op == '/') {
				j = (j == len - 1) ? len : j;
				String val = s.substring(i, j).trim();
				String tail = lst.size() > 0 ? lst.get(lst.size() - 1) : "";
				if (tail.equals("*") || tail.equals("/")) {
					String opr = lst.get(lst.size() - 2);
					int left = Integer.parseInt(opr);
					int right = Integer.parseInt(val);
					int res = tail.equals("*") ? left * right : left / right;
					lst.remove(lst.size() - 1);
					lst.remove(lst.size() - 1);
					lst.add(Integer.toString(res));
				} else {
					lst.add(val);
				}
				lst.add(Character.toString(op));
				j++;
				i = j;
			} else {
				j++;
			}
		}
		int res = Integer.parseInt(lst.get(0));
		int size = lst.size();
		for (int k = 2; k < size; k += 2) {
			String op = lst.get(k - 1);
			int right = Integer.parseInt(lst.get(k));
			res = op.equals("+") ? res + right : res - right;
		}
		return res;
	}
}
