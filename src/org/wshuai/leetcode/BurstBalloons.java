package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 7/22/2017.
 * #312 https://leetcode.com/problems/burst-balloons/
 */
public class BurstBalloons {

	//DP
	public int maxCoins(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int len = nums.length;
		int[][] aux = new int[len][len];
		//l is the length of the subrange
		for (int l = 1; l <= len; l++) {
			for (int start = 0; start <= len - l; start++) {
				int end = start + l - 1;
				for (int i = start; i <= end; i++) {
					int coins = nums[i] * (start < 1 ? 1 : nums[start - 1]) * (end >= len - 1 ? 1 : nums[end + 1]);
					coins += i != start ? aux[start][i - 1] : 0;
					coins += i != end ? aux[i + 1][end] : 0;
					aux[start][end] = Math.max(aux[start][end], coins);
				}
			}
		}
		return aux[0][len - 1];
	}

	// TLE
	public int maxCoinsDFS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] res = new int[]{Integer.MIN_VALUE};
		List<Integer> list = new ArrayList<Integer>();
		for (int i : nums) {
			list.add(i);
		}
		maxCoinsUtil(list, res, 0);
		return res[0];
	}

	private void maxCoinsUtil(List<Integer> list, int[] res, int curr) {
		if (list.size() == 1) {
			curr += list.get(0);
			res[0] = Math.max(res[0], curr);
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			int val = list.get(i);
			int left = i == 0 ? 1 : list.get(i - 1);
			int right = i == list.size() - 1 ? 1 : list.get(i + 1);
			int prod = val * left * right;
			list.remove(i);
			maxCoinsUtil(list, res, curr + prod);
			list.add(i, val);
		}
	}
}
