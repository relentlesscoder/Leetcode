package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2020.
 * #1523 https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
 */
public class CountOddNumbersInAnIntervalRange {

	public int countOdds(int low, int high) {
		int count = high - low + 1;
		if(low % 2 == 0 && high % 2 == 0){
			return (count - 1) / 2;
		}
		if(low % 2 == 1 && high % 2 == 1){
			return (count - 1) / 2 + 1;
		}
		return count / 2;
	}
}
