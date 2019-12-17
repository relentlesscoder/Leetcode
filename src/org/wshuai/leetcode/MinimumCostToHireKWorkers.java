package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Wei on 12/16/2019.
 * #857 https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 */
public class MinimumCostToHireKWorkers {
	// The key to the solution is that Kth ratio (wage / quality) can only satisfy workers with a smaller ratio.
	// Otherwise, it will break the minimum expectation constraint, since every worker will be sharing the same ratio.
	// However, you cannot simply pick the first Kth workers. Since if the sum of the quality is super high,
	// then we would rather have a higher ratio but smaller sum of quality.
	// Therefore, we are looking for the minimum sum of quality * ratio. Following is how the algorithm works:

	// 1. Create an array that stores the ratio (worker[0]) and quality (worker[1]).
	//     We don't care about the min wage expectation anymore,
	//     since we are only looking at the workers with a ratio that is smaller than any current ratio,
	//     and will always satisfy the minimum constraint.
	// 2. Sort the array and start from Kth smallest ratio.
	//     Otherwise, there won't be enough workers that satisfy with the current ratio (minimum constraint breaks)
	// 3. Keep increase the current ratio with the next worker and find the smallest sum, since both the sum of quality and ratio impacts the result
	// 4. Keep removing the largest quality, since we want the smallest sum of quality and the smallest ratio possible
	public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
		double[][] workers = new double[quality.length][2];
		for (int i = 0; i < quality.length; ++i){
			workers[i] = new double[]{(double)(wage[i]) / quality[i], (double)quality[i]};
		}
		Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
		double res = Double.MAX_VALUE, qsum = 0;
		PriorityQueue<Double> pq = new PriorityQueue<>();
		for (double[] worker: workers) {
			qsum += worker[1];
			pq.add(-worker[1]);
			if (pq.size() > K){
				qsum += pq.poll();
			}
			if (pq.size() == K){
				res = Math.min(res, qsum * worker[0]);
			}
		}
		return res;
	}
}
