package org.wshuai.leetcode;

/**
 * Created by Wei on 11/12/19.
 * #640 https://leetcode.com/problems/solve-the-equation/
 */
public class SolveTheEquation {

	public String solveEquation(String equation) {
		String[] eq = equation.split("=");
		int[] left = processEq(eq[0]);
		int[] right = processEq(eq[1]);
		if(left[0] == right[0]){
			return left[1] == right[1] ? "Infinite solutions" : "No solution";
		}
		return "x=" + (right[1] - left[1])/(left[0] - right[0]);
	}

	private int[] processEq(String eq){
		int cof = 0;
		int sum = 0;
		if(eq.charAt(0) != '-'){
			eq = "+" + eq;
		}
		int i = 0;
		int j = 1;
		while(j < eq.length()){
			if(eq.charAt(j) == '+' || eq.charAt(j) == '-'){
				if(eq.charAt(j - 1) == 'x'){
					String val = eq.substring(i, j - 1);
					cof += val.equals("+") ? 1 : val.equals("-") ? -1 : Integer.parseInt(eq.substring(i, j - 1));
				}else{
					sum += Integer.parseInt(eq.substring(i, j));
				}
				i = j;
			}
			j++;
		}
		if(eq.charAt(j - 1) == 'x'){
			String val = eq.substring(i, j - 1);
			cof += val.equals("+") ? 1 : val.equals("-") ? -1 : Integer.parseInt(eq.substring(i, j - 1));
		}else{
			sum += Integer.parseInt(eq.substring(i, j));
		}
		return new int[]{cof, sum};
	}

	private int[] processEqRegex(String exp){
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
