package org.wshuai.leetcode;

/**
 * Created by Wei on 08/01/2017.
 * #0605 https://leetcode.com/problems/can-place-flowers/
 */
public class CanPlaceFlowers {
	// time O(n)
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		for(int i = 0; i < flowerbed.length && n > 0; i++){
			if(flowerbed[i] == 1){
				continue;
			}
			int left = i > 0 ? flowerbed[i - 1] : 0;
			int right = i < flowerbed.length - 1 ? flowerbed[i + 1] : 0;
			if(left == 0 && right == 0){
				flowerbed[i] = 1;
				n--;
			}
		}
		return n == 0;
	}
}
