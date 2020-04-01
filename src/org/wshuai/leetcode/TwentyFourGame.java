package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/12/2019.
 * #0679 https://leetcode.com/problems/24-game/
 */
public class TwentyFourGame {

	private boolean res;
	private double eps;

	public boolean judgePoint24(int[] nums) {
		res = false;
		eps = 0.001;
		List<Double> doubles = new ArrayList<>();
		for(int num : nums){
			doubles.add((double)num);
		}
		dfs(doubles);
		return res;
	}

	private void dfs(List<Double> nums){
		if(res){
			return;
		}
		if(nums.size() == 1){
			if(Math.abs(nums.get(0) - 24.0) < eps){
				res = true;
			}
			return;
		}
		for(int i = 0; i < nums.size(); i++){
			for(int j = 0; j < i; j++){
				double m = nums.get(i), n = nums.get(j);
				List<Double> next = new ArrayList<>();
				next.addAll(Arrays.asList(m + n, m - n, n - m, m * n));
				if(m > eps){
					next.add(n / m);
				}
				if(n > eps){
					next.add(m / n);
				}
				nums.remove(i);
				nums.remove(j);
				for(double d : next){
					nums.add(d);
					dfs(nums);
					nums.remove(nums.size() - 1);
				}
				nums.add(j, n);
				nums.add(i, m);
			}
		}
	}
}
