package org.wshuai.leetcode;

/**
 * Created by Wei on 08/19/2019.
 * #1089 https://leetcode.com/problems/duplicate-zeros/
 */
public class DuplicateZeros {

	// time O(n), space O(1)
	public void duplicateZeros(int[] arr) {
		int n = arr.length, i = 0, j = n - 1, cnt = 0;
		// 计算执行复写0操作最远可以到达的位置i
		for (; cnt < n; i++) {
			cnt += arr[i] == 0 ? 2 : 1;
		}
		i--;
		// 两种情况：
		//   1. cnt == n 这种情况下所有在i上和之前的零都可以被
		//   复写。
		//   示例1：[1,0,2,3,0,4,5,0]
		//   示例2：[0,0,0,0,0,4,5,0]
		//   2. cnt == n + 1 这种情况下位置i上的数是0并且它只
		//   能被写一次。
		//   示例3：[8,4,5,0,0,0,0,7]
		if (cnt > n) {
			i--;
			arr[n - 1] = 0;
			j--;
		}
		// 更新剩余的位置
		for (; i >= 0; i--) {
			if (arr[i] == 0) {
				arr[j--] = 0;
				arr[j--] = 0;
			} else {
				arr[j--] = arr[i];
			}
		}
	}
}
