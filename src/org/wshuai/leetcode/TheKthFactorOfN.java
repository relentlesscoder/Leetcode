package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 06/28/2020.
 * #1492 https://leetcode.com/problems/the-kth-factor-of-n/
 */
public class TheKthFactorOfN {

	// time O(log(N)), space O(log(N))
	public int kthFactor(int n, int k) {
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i*i <= n; i++){
			if(n % i != 0){
				continue;
			}
			int r = n / i;
			if(r != i){
				list.add(r);
			}
			if(--k == 0){
				return i;
			}
		}
		return list.size() >= k ? list.get(list.size() - k) : -1;
	}
}
