package org.wshuai.leetcode;

/**
 * Created by Wei on 12/14/2020.
 * #1689 https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/
 */
public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers {

	// time O(n)
	public int minPartitions(String n) {
		int res = 0, prev = 0;
		int[] count = new int[10];
		for(char c : n.toCharArray()){
			count[c - '0']++;
		}
		for(int i = 1; i < 10; i++){
			if(count[i] > 0){
				res += i - prev;
				prev = i;
			}
		}
		return res;
	}
}
