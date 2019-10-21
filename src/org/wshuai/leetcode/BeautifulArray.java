package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/21/2019.
 * #932 https://leetcode.com/problems/beautiful-array/
 */
public class BeautifulArray {

	public int[] beautifulArray(int N) {
		ArrayList<Integer> res = new ArrayList<>();
		res.add(1);
		while (res.size() < N) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for (int i : res){
				if (i * 2 - 1 <= N){
					tmp.add(i * 2 - 1);
				}
			}
			for (int i : res){
				if (i * 2 <= N){
					tmp.add(i * 2);
				}
			}
			res = tmp;
		}
		return res.stream().mapToInt(i -> i).toArray();
	}

	private Map<Integer, int[]> map;

	public int[] beautifulArrayDivideConquer(int N) {
		map = new HashMap<>();
		return f(N);
	}

	private int[] f(int N){
		if(map.containsKey(N)){
			return map.get(N);
		}

		int[] ans = new int[N];
		if(N == 1){
			ans[0] = 1;
		}else{
			int t = 0;
			for(int x: f((N + 1) / 2)){
				ans[t++] = 2 * x - 1;
			}
			for(int x: f(N / 2)){
				ans[t++] = 2 * x;
			}
		}
		map.put(N, ans);
		return ans;
	}
}
