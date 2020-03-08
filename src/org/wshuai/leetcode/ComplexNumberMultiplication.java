package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2017.
 * #0537 https://leetcode.com/problems/complex-number-multiplication/
 */
public class ComplexNumberMultiplication {
	// time O(1)
	public String complexNumberMultiply(String a, String b) {
		// (a1 + b1i) * (a2 + b2i)
		// = a1*a2 + a1*b2*i + b1*a2*i + b1*b2i^2
		// = (a1*a2 - b1*b2) + (a1*b2 + a2*b1)*i
		int idx1 = a.indexOf("+");
		int idx2 = b.indexOf("+");
		int a1 = Integer.parseInt(a.substring(0, idx1));
		int a2 = Integer.parseInt(b.substring(0, idx2));
		int b1 = Integer.parseInt(a.substring(idx1 + 1, a.length() - 1));
		int b2 = Integer.parseInt(b.substring(idx2 + 1, b.length() - 1));
		int a3 = a1*a2 - b1*b2;
		int b3 = a1*b2 + a2*b1;
		return a3 + "+" + b3 + "i";
	}
}
