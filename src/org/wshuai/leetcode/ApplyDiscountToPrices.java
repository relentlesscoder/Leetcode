package org.wshuai.leetcode;

import java.text.DecimalFormat;

/**
 * Created by Wei on 09/18/2023.
 * #2288 https://leetcode.com/problems/apply-discount-to-prices/
 */
public class ApplyDiscountToPrices {

	private static final DecimalFormat df = new DecimalFormat("0.00");

	// time O(n), space O(1)
	public String discountPrices(String sentence, int discount) {
		String[] words = sentence.split("\\s");
		StringBuilder res = new StringBuilder();
		for (String word : words) {
			if (isPrice(word)) {
				res.append("$" + applyDiscount(Long.parseLong(word.substring(1)), discount));
			} else {
				res.append(word);
			}
			res.append(" ");
		}
		return res.substring(0, res.length() - 1);
	}

	private boolean isPrice(String word) {
		if (word.length() <= 1 || word.charAt(0) != '$') {
			return false;
		}
		for (int i = 1; i < word.length(); i++) {
			if (!Character.isDigit(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private String applyDiscount(long price, int discount) {
		double res = discount / 100d * price;
		return df.format(price - res);
	}
}
