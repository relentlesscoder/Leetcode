package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 09/09/2020.
 * #1580 https://leetcode.com/problems/put-boxes-into-the-warehouse-ii/
 */
public class PutBoxesIntoTheWarehouseII {

	// time O(n*log(n)), space O(n)
	public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
		int res = 0, min = Integer.MAX_VALUE, n = warehouse.length;
		int[] mins = new int[n];
		for(int i = 0; i < n; i++){
			min = Math.min(min, warehouse[i]);
			mins[i] = min;
		}
		min = Integer.MAX_VALUE;
		for(int i = n - 1; i >= 0; i--){
			min = Math.min(min, warehouse[i]);
			mins[i] = Math.max(mins[i], min);
		}
		Arrays.sort(mins);
		Arrays.sort(boxes);
		for(int i = 0; i < mins.length; i++){
			if(res < boxes.length && boxes[res] <= mins[i]){
				res++;
			}
		}
		return res;
	}
}
