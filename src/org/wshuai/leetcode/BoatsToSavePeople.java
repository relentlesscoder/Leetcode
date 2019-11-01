package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/1/2019.
 * #881 https://leetcode.com/problems/boats-to-save-people/
 */
public class BoatsToSavePeople {
	public int numRescueBoats(int[] people, int limit) {
		int N = people.length;
		Arrays.sort(people);
		int res = 0;
		int i = 0;
		int j = N - 1;
		while(people[j] == limit){
			res++;
			j--;
		}
		while(i < j){
			int sum = people[i] + people[j];
			if(sum <= limit){
				i++;
				j--;
			}else{
				j--;
			}
			res++;
		}
		if(i == j){
			res++;
		}
		return res;
	}
}
