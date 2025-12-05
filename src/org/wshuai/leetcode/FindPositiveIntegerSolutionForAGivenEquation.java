package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/28/2019.
 * #1237 https://leetcode.com/problems/find-positive-integer-solution-for-a-given-equation/
 */
public class FindPositiveIntegerSolutionForAGivenEquation {

	//O(x + y)
	public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
		List<List<Integer>> lst = new ArrayList<>();
		int x = 1, y = 1000;
		while(x <= 1000 && y > 0){
			int res = customfunction.f(x, y);
			if(res == z){
				lst.add(Arrays.asList(x++, y--));
			}else if(res < z){
				x++;
			}else{
				y--;
			}
		}
		return lst;
	}

	// O(x * log(y))
	public List<List<Integer>> findSolutionBinarySearch(CustomFunction customfunction, int z) {
		List<List<Integer>> lst = new ArrayList<>();
		for(int i = 1; i <= 1000; i++){
			int left = 1;
			int right = 1000;
			while(left <= right){
				int mid = (left + right) / 2;
				int res = customfunction.f(i, mid);
				if(res == z){
					lst.add(Arrays.asList(i, mid));
					break;
				}else if(res > z){
					right = mid - 1;
				}else{
					left = mid + 1;
				}
			}
		}
		return lst;
	}

	// This is the custom function interface.
	// You should not implement it, or speculate about its implementation
	private class CustomFunction {
		// Returns f(x, y) for any given positive integers x and y.
		// Note that f(x, y) is increasing with respect to both x and y.
		// i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
		public int f(int x, int y){
			return 0;
		}
	}
}
