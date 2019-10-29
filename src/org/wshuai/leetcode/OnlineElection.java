package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/29/19.
 * #911 https://leetcode.com/problems/online-election/
 */
public class OnlineElection {

	private int[] M;
	private Map<Integer, Integer> votes;
	private int[] T;
	//private Map<Integer, Integer> cache;

	// commented cached version
	public OnlineElection(int[] persons, int[] times) {
		M = new int[persons.length];
		votes = new HashMap<>();
		//cache = new HashMap<>();
		int max = 1;
		M[0] = persons[0];
		votes.put(persons[0], 1);
		for(int i = 1; i < persons.length; i++){
			int cnt = votes.getOrDefault(persons[i], 0) + 1;
			if(cnt >= max){
				max = cnt;
				M[i] = persons[i];
			}else{
				M[i] = M[i - 1];
			}
			votes.put(persons[i], cnt);
		}
		T = times;
	}

	// binary search to get the largest time point
	// that are
	public int q(int t) {
		//if(!cache.containsKey(t)){
			int left = 0;
			int right = T.length - 1;
			while(left < right){
				int mid = (left + right) / 2;
				if(T[mid] < t){
					left = mid + 1;
				}else{
					right = mid;
				}
			}
			left = T[left] > t ? left - 1 : left;
			return M[left];
			//cache.put(t, M[left]);
		//}
		//return cache.get(t);
	}
}
