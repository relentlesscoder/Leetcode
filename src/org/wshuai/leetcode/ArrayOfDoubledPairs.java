package org.wshuai.leetcode;

import java.util.TreeMap;

/**
 * Created by Wei on 11/5/2019.
 * #954 https://leetcode.com/problems/array-of-doubled-pairs/
 */
public class ArrayOfDoubledPairs {
	public boolean canReorderDoubled(int[] A) {
		int[] count = new int[100_001];
		for(int a : A){
			if(a >= 0){
				count[a]++;
			}
		}
		int sum = countSum(count);
		if(sum != 0){
			return false;
		}
		for(int a : A){
			if(a < 0){
				count[a * -1]++;
			}
		}
		sum = countSum(count);
		return sum == 0;
	}

	private int countSum(int[] count){
		int sum = 0;
		for(int i = 0; i <= 100_000; i++){
			if(count[i] > 0){
				int key = i << 1;
				if(key > 100_000 || count[key] < count[i]){
					return -1;
				}
				count[key] -= count[i];
				count[i] = 0;
			}
		}
		for(int c : count){
			sum += c;
		}
		return sum;
	}

	public boolean canReorderDoubledSortedMap(int[] A) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int zeros = 0;
		for(int a : A){
			if(a == 0){
				zeros++;
				continue;
			}
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		if(zeros % 2 != 0){
			return false;
		}
		for(int k : map.keySet()){
			if(map.get(k) == 0){
				continue;
			}
			if(k < 0 && k % 2 != 0){
				return false;
			}
			int target = k > 0 ? k << 1 : k >> 1;
			int targetCount = map.getOrDefault(target, 0);
			if(targetCount < map.get(k)){
				return false;
			}
			map.put(target, targetCount - map.get(k));
		}
		return true;
	}
}
