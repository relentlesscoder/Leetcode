package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/12/19.
 * #910 https://leetcode.com/problems/smallest-range-ii/
 */
public class SmallestRangeII {
	// https://www.cnblogs.com/grandyang/p/11361245.html
	public int smallestRangeII(int[] A, int K) {
		Arrays.sort(A);
		int N = A.length;
		int left = A[0] + K;
		int right = A[N - 1] - K;
		int res = A[N - 1] - A[0];
		for(int i = 0; i < N - 1; i++){
			int max = Math.max(right, A[i] + K);
			int min = Math.min(left, A[i + 1] - K);
			res = Math.min(res, max - min);
		}
		return res;
	}
}
/*      如果不考虑数字修改，那么原数组的最大值最小值之间的差值就是所求，这里可以当作结果 res 的初始值。
		由于每个数字都需要加K或者减K，为了使得新数组的最大值最小值的差值最小，应该尽量使原数组中
		的较小的数字加K，较大的数字减K，所以最好是先给原数组排个序，然后在数组的某个位置i为界限，
		将原数组分为两段，前面所有的数字都加K，后面所有的数字都减K。则前半段 [0, i] 中的最大值是
		A[i]+K，最小值是 A[0]+K，后半段 [i+1, n-1] 范围内的最大值是 A[n-1]-K，最小值是 A[i+1]-K，
		所以整个数组的最大值是 A[i]+K 和 A[n-1]-K 中的较大值，最小值是 A[0]+K 和 A[i+1]-K 中的
		较小值，二者做差就是可能的结果了，遍历所有的i，用每次计算出的差值来更新结果 res 即可                      */
