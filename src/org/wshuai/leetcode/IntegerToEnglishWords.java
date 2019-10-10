package org.wshuai.leetcode;

/**
 * Created by Wei on 7/6/2017.
 * #273 https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {
	private static final String[] singles = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	private static final String[] doubles = new String[]{"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] tens = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

	public String numberToWords(int num) {
		String result = "";
		if (num == 0) {
			return "Zero";
		}
		int quotient = num / 1000000000;
		int remainder = num % 1000000000;
		if (quotient > 0) {
			result += parseInteger(quotient) + " Billion ";
		}
		if (remainder > 0) {
			quotient = remainder / 1000000;
			remainder = remainder % 1000000;
			if (quotient > 0) {
				result += parseInteger(quotient) + " Million ";
			}
		}
		if (remainder > 0) {
			quotient = remainder / 1000;
			remainder = remainder % 1000;
			if (quotient > 0) {
				result += parseInteger(quotient) + " Thousand ";
			}
		}
		if (remainder > 0) {
			result += parseInteger(remainder);
		}
		return result.trim();
	}

	private String parseInteger(int num) {
		String result = "";
		int quotient = num / 100;
		int remainder = num % 100;
		if (quotient > 0) {
			result += singles[quotient] + " Hundred ";
		}
		if (remainder > 0) {
			quotient = remainder / 10;
			remainder = remainder % 10;
			if (quotient == 1) {
				result += remainder == 0 ? tens[quotient] : doubles[remainder];
			} else if (quotient > 1) {
				result += tens[quotient];
				if (remainder > 0) {
					result += " " + singles[remainder];
				}
			} else {
				result += singles[remainder];
			}
		}
		return result.trim();
	}
}
