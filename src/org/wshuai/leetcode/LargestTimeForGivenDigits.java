package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #949 https://leetcode.com/problems/largest-time-for-given-digits/
 */
public class LargestTimeForGivenDigits {
	int maxTime;
	String max;
	int[] res;
	boolean[] used;

	public String largestTimeFromDigits(int[] A) {
		maxTime = -1;
		max = "";
		res = new int[4];
		used = new boolean[4];
		dfs(A, 0);
		return max;
	}

	private void dfs(int[] A, int index) {
		if (index >= 4) {
			if ((res[0] < 2 || (res[0] == 2 && res[1] <= 3)) && res[2] <= 5) {
				int curr = (res[0] * 10 + res[1]) * 60 + res[2] * 10 + res[3];
				if (curr > maxTime) {
					maxTime = curr;
					max = "" + res[0] + res[1] + ":" + res[2] + res[3];
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (used[i]) {
				continue;
			}
			used[i] = true;
			res[index] = A[i];
			dfs(A, index + 1);
			res[index] = 0;
			used[i] = false;
		}
	}
}
