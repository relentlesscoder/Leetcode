package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/31/2020.
 * #1564 https://leetcode.com/problems/put-boxes-into-the-warehouse-i/
 */
public class PutBoxesIntoTheWarehouseI {

	// time O(n*log(n))
	public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
		int res = 0, n = warehouse.length;
		Arrays.sort(boxes);
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++){
			min = Math.min(min, warehouse[i]);
			warehouse[i] = min;
		}
		for(int i = 0, j = n - 1; i < boxes.length && j >= 0; i++){
			while(j >= 0 && warehouse[j] < boxes[i]){
				j--;
			}
			if(j >= 0){
				res++;
				j--;
			}
		}
		return res;
	}
}
