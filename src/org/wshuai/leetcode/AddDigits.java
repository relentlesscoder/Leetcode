package org.wshuai.leetcode;

/**
 * Created by Wei on 10/26/2016.
 * #0258 https://leetcode.com/problems/add-digits/
 */
public class AddDigits {
	// time O(d^2)
	public int addDigits(int num) {
		while(num >= 10){
			int temp = num, next = 0;
			while(temp > 0){
				next += temp % 10;
				temp /= 10;
			}
			num = next;
		}
		return num;
	}

	// time O(1)
	//http://www.cnblogs.com/grandyang/p/4741028.html
	public int addDigitsMath(int num) {
		return 1 + (num - 1) % 9;
	}
}
