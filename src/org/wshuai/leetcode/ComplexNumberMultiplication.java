package org.wshuai.leetcode;

/**
 * Created by Wei on 8/1/2017.
 * #537 https://leetcode.com/problems/complex-number-multiplication/
 */
public class ComplexNumberMultiplication {
	//(a+bi)*(c+di) = ac + adi + bci + dbi^2 = (ac-bd)+(ad+bc)i
	public String complexNumberMultiply(String a, String b) {
		int idx1 = a.indexOf("+");
		int idx2 = b.indexOf("+");
		int a1 = Integer.parseInt(a.substring(0, idx1));
		int a2 = Integer.parseInt(b.substring(0, idx2));
		int b1 = Integer.parseInt(a.substring(idx1 + 1, a.length() - 1));
		int b2 = Integer.parseInt(b.substring(idx2 + 1, b.length() - 1));
		int a3 = a1 * a2 - b1 * b2;
		int b3 = a1 * b2 + a2 * b1;
		return Integer.toString(a3) + "+" + Integer.toString(b3) + "i";
	}
}
