package org.wshuai.leetcode;

/**
 * Created by Wei on 10/19/2019.
 * #1052 https://leetcode.com/problems/grumpy-bookstore-owner/
 */
public class GrumpyBookstoreOwner {
	public int maxSatisfied(int[] customers, int[] grumpy, int X) {
		int res = 0;
		for(int i = 0; i < customers.length; i++){
			res += grumpy[i] == 0 ? customers[i] : 0;
		}
		int max = 0;
		int curr = 0;
		int i = 0;
		int j = 0;
		while(j < customers.length && j - i + 1 <= X){
			curr += grumpy[j] == 1 ? customers[j] : 0;
			if(j - i + 1 == X){
				max = Math.max(max, curr);
				curr -= grumpy[i] == 1 ? customers[i] : 0;
				i++;
			}
			j++;
		}
		return res + max;
	}
}
