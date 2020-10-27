package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2017.
 * #0273 https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {

	private static final String[] ONES = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	private static final String[] TENPLUS = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] TENS = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] THOUSANDS = new String[]{" Billion", " Million", " Thousand", ""};

	// time O(1)
	public String numberToWords(int num) {
		if(num == 0){
			return "Zero";
		}
		int[] arr = new int[4];
		for(int i = 0; i < 4; i++){
			arr[3 - i] = num % 1_000;
			num /= 1_000;
		}
		StringBuilder res = new StringBuilder();
		for(int i = 0; i < 4; i++){
			if(arr[i] == 0){
				continue;
			}
			res.append(toWord(arr[i])).append(THOUSANDS[i]).append(" ");
		}
		return res.toString().trim();
	}

	private String toWord(int num){
		StringBuilder res = new StringBuilder();
		int h = num / 100;
		if(h > 0){
			res.append(ONES[h]).append(" Hundred ");
		}
		int t = (num % 100) / 10;
		if(t == 1){
			int val = num % 100;
			res.append(TENPLUS[val - 10]);
			return res.toString();
		}else if(t > 1){
			res.append(TENS[t]).append(" ");
		}
		int d = num % 10;
		if(d > 0){
			res.append(ONES[d]);
		}
		return res.toString().trim();
	}
}
