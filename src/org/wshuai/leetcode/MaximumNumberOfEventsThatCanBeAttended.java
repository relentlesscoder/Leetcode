package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 02/18/2020.
 * #1353 https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 */
public class MaximumNumberOfEventsThatCanBeAttended {
	public int maxEvents(int[][] events) {
		Arrays.sort(events, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
		Set<Integer> set = new HashSet<>();
		for(int[] e : events){
			if(e[1] == e[0]){
				set.add(e[0]);
			}else{
				for(int i = e[0]; i <= e[1]; i++){
					if(!set.contains(i)){
						set.add(i);
						break;
					}
				}
			}
		}
		return set.size();
	}
}
