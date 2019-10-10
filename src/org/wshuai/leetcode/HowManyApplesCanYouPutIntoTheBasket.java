package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 9/30/2019.
 * #1196 https://leetcode.com/problems/how-many-apples-can-you-put-into-the-basket/
 */
public class HowManyApplesCanYouPutIntoTheBasket {
	public int maxNumberOfApples(int[] arr) {
		Arrays.sort(arr);
		int count = 0;
		int total = 0;
		for(int i = 0; i < arr.length; i++){
			total += arr[i];
			if(total >= 5_000){
				break;
			}
			count++;
		}
		return count;
	}
}
