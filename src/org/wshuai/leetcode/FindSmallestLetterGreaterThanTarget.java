package org.wshuai.leetcode;

/**
 * Created by Wei on 08/21/2019.
 * #0744 https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 */
public class FindSmallestLetterGreaterThanTarget {
	// time O(log(n))
	public char nextGreatestLetter(char[] letters, char target) {
		int low = 0, high = letters.length;
		while(low < high){
			int mid = low + ((high - low) >> 1);
			if(letters[mid] <= target){
				low = mid + 1;
			}else{
				high = mid;
			}
		}
		return letters[low % letters.length];
	}
}
