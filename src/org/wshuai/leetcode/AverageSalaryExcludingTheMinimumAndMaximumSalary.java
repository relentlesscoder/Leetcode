package org.wshuai.leetcode;

/**
 * Created by Wei on 06/28/2020.
 * #1491 https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/
 */
public class AverageSalaryExcludingTheMinimumAndMaximumSalary {

	// time O(n)
	public double average(int[] salary) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
		for(int s : salary){
			sum += s;
			min = Math.min(min, s);
			max = Math.max(max, s);
		}
		return (sum - min - max) * 1.0 / (salary.length - 2);
	}
}
