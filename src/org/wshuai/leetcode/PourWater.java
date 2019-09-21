package org.wshuai.leetcode;

/**
 * Created by Wei on 9/20/19.
 * #755 https://leetcode.com/problems/pour-water/
 */
public class PourWater {
	private int[] heights;

	public int[] pourWater(int[] heights, int V, int K) {
		this.heights = heights;
		for(int i = 0; i < V; i++){
			int left;
			int right;
			if((left = moveLeft(K)) != K){
				heights[left]++;
			}else if((right = moveRight(K)) != K){
				heights[right]++;
			}else{
				heights[K]++;
			}
		}
		return heights;
	}

	private int moveLeft(int k){
		int j = k - 1;
		while(j >= 0){
			if(heights[j] < heights[k]){
				k = j;
				j--;
			}else if(heights[j] == heights[k]){
				j--;
			}else{
				return k;
			}
		}
		return k;
	}

	private int moveRight(int k){
		int j = k + 1;
		while(j < heights.length){
			if(heights[j] < heights[k]){
				k = j;
				j++;
			}else if(heights[j] == heights[k]){
				j++;
			}else{
				return k;
			}
		}
		return k;
	}
}
