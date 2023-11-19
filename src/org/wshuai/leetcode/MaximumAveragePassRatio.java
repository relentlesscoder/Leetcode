package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 09/20/2023.
 * #1792 https://leetcode.com/problems/maximum-average-pass-ratio/
 */
public class MaximumAveragePassRatio {

	// time O(m * log(n)), space O(n)
	public double maxAverageRatio(int[][] classes, int extraStudents) {
		int n = classes.length;
		PriorityQueue<double[]> queue = new PriorityQueue<>((a, b) -> {
			if (a[0] == b[0]) {
				return 0;
			}
			return a[0] > b[0] ? -1 : 1;
		});
		for (int[] c : classes) {
			double pass = c[0] * 1.0, total = c[1] * 1.0;
			queue.offer(new double[] {getProfit(pass, total), pass, total});
		}
		while (extraStudents-- > 0) {
			double[] maxProfit = queue.poll();
			double pass = maxProfit[1], total = maxProfit[2];
			queue.offer(new double[] {getProfit(pass + 1, total + 1), pass + 1, total + 1});
		}
		double totalAvg = 0.0;
		while (!queue.isEmpty()) {
			totalAvg += queue.peek()[1] / queue.poll()[2];
		}
		return totalAvg / n;
	}

	private double getProfit(double pass, double total) {
		double before = pass / total, after = (pass + 1.0) / (total + 1.0);
		return after - before;
	}
}
