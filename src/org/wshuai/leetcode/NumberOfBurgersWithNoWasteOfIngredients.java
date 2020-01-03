package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/3/19.
 * #1276 https://leetcode.com/problems/number-of-burgers-with-no-waste-of-ingredients/
 */
public class NumberOfBurgersWithNoWasteOfIngredients {
	public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
		int diff = tomatoSlices - 2 * cheeseSlices;
		if(cheeseSlices >= 0 && diff >= 0 && diff % 2 == 0){
			int jumbo = diff / 2;
			if(cheeseSlices >= jumbo){
				return Arrays.asList(jumbo, cheeseSlices - jumbo);
			}
		}
		return new ArrayList<>();
	}
}
