package org.wshuai.leetcode;

/**
 * Created by Wei on 12/26/2019.
 * #1088 https://leetcode.com/problems/confusing-number-ii/
 */
public class ConfusingNumberII {
	private static int[] nums = new int[]{0, 1, 6, 8, 9};
	private int[] map;
	private int res;

	public int confusingNumberII(int N) {
		map = new int[10];
		map[0] = 0;
		map[1] = 1;
		map[6] = 9;
		map[8] = 8;
		map[9] = 6;
		res = 0;
		dfs(N, 0);
		return res;
	}

	private void dfs(int N, long cur){
		if(cur > N){
			return;
		}
		if(cur <= N && confusingNumber(cur)){
			res++;
		}
		int i = cur == 0 ? 1 : 0;
		for(; i < 5; i++){
			dfs(N, cur * 10 + nums[i]);
		}
	}

	public boolean confusingNumber(long N) {
		long x = 0;
		long y = N;
		while(y > 0){
			int i = (int)(y % 10);
			x *= 10;
			x += map[i];
			y /= 10;
		}
		return x != N;
	}
}
