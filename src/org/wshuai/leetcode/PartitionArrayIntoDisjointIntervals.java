package org.wshuai.leetcode;

/**
 * Created by Wei on 11/5/2019.
 * #915 https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 */
public class PartitionArrayIntoDisjointIntervals {
	public int partitionDisjoint(int[] A) {
		int N = A.length;
		int[] maxFromLeftInc = new int[N];
		int[] minFromRightExc = new int[N];
		int max = A[0];
		for(int i = 0 ; i < N; i++){
			max = Math.max(A[i], max);
			maxFromLeftInc[i] = max;
		}
		int min = A[N - 1];
		for(int i = N - 2; i >= 0; i--){
			min = Math.min(A[i + 1], min);
			minFromRightExc[i] = min;
		}
		int k = 0;
		for(; k < N - 1; k++){
			if(maxFromLeftInc[k] <= minFromRightExc[k]){
				break;
			}
		}
		return k + 1;
	}
}
