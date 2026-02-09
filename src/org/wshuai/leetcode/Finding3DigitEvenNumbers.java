package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 10/29/2023.
 * #2094 https://leetcode.com/problems/finding-3-digit-even-numbers/
 */
public class Finding3DigitEvenNumbers {

	// time O(n), space O(n)
	public int[] findEvenNumbers(int[] digits) {
		int[] counter = new int[10];
		// 统计每个数字的出现次数
		for (int d : digits) {
			counter[d]++;
		}
		List<Integer> list = new ArrayList<>();
		// 遍历所有三位数
		for (int i = 100; i < 1_000; i += 2) {
			int d0 = i % 10, d1 = (i / 10) % 10, d2 = i / 100;
			counter[d0]--;
			counter[d1]--;
			counter[d2]--;
			// 如果给的数字可以组成这个三位数则加入答案
			if (counter[d0] >= 0 && counter[d1] >= 0 && counter[d2] >= 0) {
				list.add(i);
			}
			counter[d0]++;
			counter[d1]++;
			counter[d2]++;
		}
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}
		return res;
	}

	// time O(C(n, 3)), space O(1)
	public int[] findEvenNumbersDFS(int[] digits) {
		Arrays.sort(digits);
		List<Integer> nums = new ArrayList<>();
		dfs(0, digits, new boolean[digits.length], nums);
		int[] res = new int[nums.size()];
		Arrays.setAll(res, i -> nums.get(i));
		return res;
	}

	private void dfs(int val, int[] digits, boolean[] used, List<Integer> nums) {
		if (val >= 100) {
			if (val % 2 == 0) {
				nums.add(val);
			}
			return;
		}
		int visited = 0; // 在递归树本层中用过的数字
		for (int i = 0; i < digits.length; i++) {
			// 当前位置没有被用并且当前位置的数字在本层中没有用过
			if (used[i] || ((1 << digits[i]) & visited) > 0) {
				continue;
			}
			// 0 不能是第一个数字
			if (val == 0 && digits[i] == 0) {
				continue;
			}
			visited |= (1 << digits[i]);
			used[i] = true;
			dfs(val * 10 + digits[i], digits, used, nums);
			used[i] = false;
		}
	}
}
