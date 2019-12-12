package org.wshuai.leetcode;

/**
 * Created by Wei on 12/12/2019.
 * #1231 https://leetcode.com/problems/divide-chocolate/
 */
public class DivideChocolate {
	public int maximizeSweetness(int[] sweetness, int K) {
		int left = 100_000;
		int right = 0;
		for(int s : sweetness){
			left = Math.min(left, s);
			right += s;
		}
		right /= (K + 1);
		while(left < right){
			int mid = (right + left + 1) / 2;
			// equals >= K + 1
			if(countPartitions(sweetness, mid) > K){
				left = mid;
			}else{
				right = mid - 1;
			}
		}
		return left;
	}

	private int countPartitions(int[] S, int v){
		int cnt = 0;
		int cur = 0;
		for(int s : S){
			cur += s;
			if(cur >= v){
				cnt += 1;
				cur = 0;
			}
		}
		return cnt;
	}
}
