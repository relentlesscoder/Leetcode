package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #2303 https://leetcode.com/problems/calculate-amount-paid-in-taxes/
 */
public class CalculateAmountPaidInTaxes {

	// time O(n), space O(1)
	public double calculateTax(int[][] brackets, int income) {
		double taxPaid = 0.0;
		for (int i = 0; i < brackets.length && income > 0; i++) {
			int taxableIncome = Math.min(brackets[i][0] - (i == 0 ? 0 : brackets[i - 1][0]), income);
			taxPaid += 1.0 * taxableIncome * brackets[i][1] / 100.0;
			income -= taxableIncome;
		}
		return taxPaid;
	}
}
