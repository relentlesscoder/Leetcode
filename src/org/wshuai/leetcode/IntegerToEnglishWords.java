package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2017.
 * #0273 https://leetcode.com/problems/integer-to-english-words/
 */
public class IntegerToEnglishWords {
	private static final String[] singles = new String[]{"One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
	private static final String[] doubles = new String[]{"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	private static final String[] tens = new String[]{"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};

	public String numberToWords(int num) {
		if(num == 0){
			return "Zero";
		}
		int[] arr = new int[4];
		String[] map = new String[]{" Billion", " Million", " Thousand", ""};
		for(int i = 0, j = 3; i < 4; i++){
			arr[j--] = num % 1_000;
			num /= 1_000;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 4; i++){
			if(arr[i] == 0){
				continue;
			}
			sb.append(toWord(arr[i])).append(map[i]).append(" ");
		}
		return sb.toString().trim();
	}

	private String toWord(int num){
		StringBuilder sb = new StringBuilder();
		int h = num / 100;
		if(h > 0){
			sb.append(singles[h - 1]).append(" ").append("Hundred ");
		}
		int t = (num % 100) / 10;
		if(t == 1){
			int val = num % 100;
			sb.append(doubles[val - 10]);
			return sb.toString();
		}else if(t > 1){
			sb.append(tens[t - 2]).append(" ");
		}
		int d = num % 10;
		if(d > 0){
			sb.append(singles[d - 1]);
		}
		return sb.toString().trim();
	}
}
