package org.wshuai.leetcode;

import java.util.Scanner;

/**
 * Created by Wei on 11/12/2019.
 * #0592 https://leetcode.com/problems/fraction-addition-and-subtraction/
 */
public class FractionAdditionAndSubtraction {
	// time O(n)
	// a/b + x/y = (a*y+b*x)/b*y
	public String fractionAddition(String expression) {
		// https://leetcode.com/problems/fraction-addition-and-subtraction/discuss/103384/Small-simple-C%2B%2BJavaPython
		// https://stackoverflow.com/questions/2973436/regex-lookahead-lookbehind-and-atomic-groups
		Scanner sc = new Scanner(expression).useDelimiter("/|(?=[-+])");
		int A = 0, B = 1;
		while (sc.hasNext()) {
			int a = sc.nextInt(), b = sc.nextInt();
			A = A * b + a * B;
			B *= b;
			int g = gcd(A, B);
			A /= g;
			B /= g;
		}
		return A + "/" + B;
	}

	private int gcd(int x, int y){
		return x == 0 ? Math.abs(y) : gcd(y % x, x);
	}
}
