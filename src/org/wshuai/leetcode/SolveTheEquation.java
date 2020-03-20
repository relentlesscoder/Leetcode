package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/2019.
 * #0640 https://leetcode.com/problems/solve-the-equation/
 */
public class SolveTheEquation {

	// time O(n)
	public String solveEquation(String equation) {
		String[] eq = equation.split("=");
		int[] left = processEq(eq[0]);
		int[] right = processEq(eq[1]);
		if(left[0] == right[0]){
			return left[1] == right[1] ? "Infinite solutions" : "No solution";
		}
		return "x=" + (right[1] - left[1])/(left[0] - right[0]);
	}

	private int[] processEq(String exp){
		// https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
		String[] tokens = exp.split("(?=[-+])");
		int[] res =  new int[2];
		for (String token : tokens) {
			if (token.equals("+x") || token.equals("x")){
				res[0] += 1;
			}else if (token.equals("-x")){
				res[0] -= 1;
			}else if (token.contains("x")){
				res[0] += Integer.parseInt(token.substring(0, token.indexOf("x")));
			}else{
				res[1] += Integer.parseInt(token);}
		}
		return res;
	}
}
