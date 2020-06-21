package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #0774 https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 */
public class MinimizeMaxDistanceToGasStation {

	// time O(n*log(m))
	public double minmaxGasDist(int[] stations, int K) {
		int n = stations.length;
		double left = 0.0, right = 0.0;
		for(int i = 0; i < n - 1; i++){
			right = Math.max(stations[i + 1] - stations[i], right);
		}
		while(right - left > 1e-6){
			double mid = left + (right - left) / 2.0;
			int count = 0;
			for(int i = 0; i < n - 1; i++){
				// ceil(9/3.33) = ceil(2.70) = 3
				// needs to add 3 - 1 = 2 stations to create 3 partitions
				// note that we cannot simple use total length / n + k
				// since the stations may not necessarily be evenly distributed
				count += (int)Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
			}
			if(count <= K){
				right = mid;
			}else{
				left = mid;
			}
		}
		return left;
	}
}
