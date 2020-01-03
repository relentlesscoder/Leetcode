package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 12/12/2019.
 * #774 https://leetcode.com/problems/minimize-max-distance-to-gas-station/
 */
public class MinimizeMaxDistanceToGasStation {

	// binary search
	public double minmaxGasDist(int[] stations, int K) {
		int n = stations.length;
		double left = 0;
		double right = stations[n - 1] - stations[0];
		// tolerance is 1e-6
		while(right - left > 1e-6){
			double mid = left + (right - left)/2.0;
			int cnt = 0;
			for(int i = 0; i < n - 1; i++){
				// same as Math.ceil((stations[i + 1] - stations[i]) / mid) - 1
				// ceil(9/3.33) = ceil(2.70) = 3
				// needs to add 3 - 1 = 2 stations to create 3 partitions
				cnt += (int)((stations[i + 1] - stations[i]) / mid);
			}
			// should not do mid + 1 for
			// binary search on double type
			if(cnt <= K){
				right = mid;
			}else{
				left = mid;
			}
		}
		return left;
	}

	// super clean solution (TLE)
	public double minmaxGasDistGreedy(int[] stations, int K) {
		PriorityQueue<double[]> pq = new PriorityQueue<double[]>(
				(a,b) -> (a[0]/a[1] < b[0]/b[1]) ? 1 : -1);
		for(int i = 1; i < stations.length; ++i){
			pq.add(new double[]{stations[i] - stations[i-1], 1.0});
		}
		while(K-- > 0){
			double [] cur = pq.poll();
			//System.out.println(cur[0]);
			++cur[1];
			pq.add(cur);
		}
		double[] value =  pq.poll();
		return value[0]/value[1];
	}
}
