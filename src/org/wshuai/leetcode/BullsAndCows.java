package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/21/2016.
 * #0299 https://leetcode.com/problems/bulls-and-cows/
 */
public class BullsAndCows {
	// time O(n)
	public String getHint(String secret, String guess) {
		int a = 0, b = 0, n = secret.length();
		int[] map = new int[10];
		for(int i = 0; i < n; i++){
			int n1 = secret.charAt(i) - '0', n2 = guess.charAt(i) - '0';
			if(n1 == n2){
				a++;
			}else{
				if(map[n2] > 0){
					b++;
				}
				if(map[n1] < 0){
					b++;
				}
				map[n1]++;
				map[n2]--;
			}
		}
		return a + "A" + b + "B";
	}

	// time O(n)
	public String getHintMap(String secret, String guess) {
		int a = 0, b = 0;
		int[] map1 = new int[10], map2 = new int[10];
		for(int i = 0; i < secret.length(); i++){
			char c1 = secret.charAt(i), c2 = guess.charAt(i);
			int k1 = c1 - '0', k2 = c2 - '0';
			if(c1 == c2){
				a++;
				continue;
			}
			if(map1[k2] > 0){
				map1[k2]--;
				b++;
			}else{
				map2[k2]++;
			}
			if(map2[k1] > 0){
				map2[k1]--;
				b++;
			}else{
				map1[k1]++;
			}
		}
		return a + "A" + b + "B";
	}

	// time O(n*log(n))
	public String getHintSorting(String secret, String guess) {
		int a = 0, b = 0, n = secret.length(), i = 0, j = 0;
		char[] arr1 = secret.toCharArray(), arr2 = guess.toCharArray();
		for(int k = 0; k < n; k++){
			if(arr1[k] == arr2[k]){
				a++;
			}
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		while(i < n && j < n){
			if(arr1[i] == arr2[j]){
				i++;
				j++;
				b++;
			}else if(arr1[i] > arr2[j]){
				j++;
			}else{
				i++;
			}
		}
		return a + "A" + (b - a) + "B";
	}
}
