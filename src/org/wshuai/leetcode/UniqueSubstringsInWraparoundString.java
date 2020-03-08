package org.wshuai.leetcode;

/**
 * Created by Wei on 11/05/2019.
 * #0467 https://leetcode.com/problems/unique-substrings-in-wraparound-string/
 */
public class UniqueSubstringsInWraparoundString {
	// time O(n)
	// https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95439/Concise-Java-solution-using-DP
	public int findSubstringInWraproundString(String p) {
		int[] maxLengthEndWith = new int[26];
		int res = 0, curLengthEndWith = 0;
		char[] arr = p.toCharArray();
		for(int i = 0; i < p.length(); i++){
			if(i > 0 && (arr[i] - arr[i - 1] == 1 || arr[i - 1] - arr[i] == 25)){
				curLengthEndWith++;
			}else{
				curLengthEndWith = 1;
			}
			int index = arr[i] - 'a';
			maxLengthEndWith[index] = Math.max(maxLengthEndWith[index], curLengthEndWith);
		}
		for(int max : maxLengthEndWith){
			res += max;
		}
		return res;
	}
}
