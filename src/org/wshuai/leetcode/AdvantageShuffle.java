package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Wei on 11/7/2019.
 * #870 https://leetcode.com/problems/advantage-shuffle/
 */
public class AdvantageShuffle {
	public int[] advantageCount(int[] A, int[] B) {
		int N = A.length;
		int[] res = new int[N];
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int a : A){
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		Arrays.fill(res, -1);
		for(int i = 0; i < N; i++){
			Integer k = map.higherKey(B[i]);
			if(k != null && map.get(k) > 0){
				res[i] = k;
				int cnt = map.get(k);
				if(cnt == 1){
					map.remove(k);
				}else{
					map.put(k, cnt - 1);
				}
			}
		}
		int i = 0;
		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			int v = entry.getKey();
			int c = entry.getValue();
			while(c-- > 0){
				while(i < N && res[i] != -1){
					i++;
				}
				res[i] = v;
			}
		}
		return res;
	}
}
