package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #0774 https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 */
public class MinimizeMaxDistanceToGasStation {

	// time O(n * log(max)), space O(1)
	public double minmaxGasDist(int[] stations, int k) {
		double low = 0.0, high = 0.0;
		for (int i = 1; i < stations.length; i++) {
			high = Math.max(high, 1.0 * (stations[i] - stations[i - 1]));
		}
		while (high - low > 1e-6) {
			double mid = low + (high - low) / 2.0;
			int count = 0;
			for (int i = 1; i < stations.length; i++) {
				// ceil(9/3.33) = ceil(2.70) = 3
				// needs to add 3 - 1 = 2 stations to create 3 partitions
				// note that we cannot simply use total length / n + k
				// since the stations may not necessarily be evenly distributed
				count += (int)Math.ceil((stations[i] - stations[i - 1]) / mid) - 1;
			}
			if (count <= k) {
				high = mid;
			} else {
				low = mid;
			}
		}
		return low;
	}
}
