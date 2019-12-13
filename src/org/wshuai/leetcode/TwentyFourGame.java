package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/12/2019.
 * #679 https://leetcode.com/problems/24-game/
 */
public class TwentyFourGame {
	boolean res = false;
	final double eps = 0.001;

	public boolean judgePoint24(int[] nums) {
		List<Double> arr = new ArrayList<>();
		for(int n: nums) arr.add((double) n);
		dfs(arr);
		return res;
	}

	private void dfs(List<Double> arr){
		if(res) return;
		if(arr.size() == 1){
			if(Math.abs(arr.get(0) - 24.0) < eps)
				res = true;
			return;
		}
		for (int i = 0; i < arr.size(); i++) {
			for (int j = 0; j < i; j++) {
				List<Double> next = new ArrayList<>();
				Double p1 = arr.get(i), p2 = arr.get(j);
				next.addAll(Arrays.asList(p1 + p2, p1 - p2, p2 - p1, p1 * p2));
				if(Math.abs(p2) > eps)  next.add(p1 / p2);
				if(Math.abs(p1) > eps)  next.add(p2 / p1);

				arr.remove(i);
				arr.remove(j);
				for (Double n: next){
					arr.add(n);
					dfs(arr);
					arr.remove(arr.size()-1);
				}
				arr.add(j, p2);
				arr.add(i, p1);
			}
		}
	}
}
