package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2017.
 * #0552 https://leetcode.com/problems/student-attendance-record-ii/
 */
public class StudentAttendanceRecordII {
	private static final int mod = 1_000_000_007;

	// time O(n), space O(n)
	// https://leetcode.com/problems/student-attendance-record-ii/discuss/101643/Share-my-O(n)-C%2B%2B-DP-solution-with-thinking-process-and-explanation
	public int checkRecord(int n) {
		if (n == 1) {
			return 3;
		}
		if (n == 2) {
			return 8;
		}
		int[] A = new int[n], P = new int[n], L = new int[n];
		P[0] = A[0] = L[0] = 1;
		L[1] = 3;
		A[1] = 2;
		A[2] = 4;
		for (int i = 1; i < n; i++) {
			P[i] = ((A[i - 1] + P[i - 1]) % mod + L[i - 1]) % mod;
			if (i > 1) {
				L[i] = ((A[i - 1] + P[i - 1]) % mod + (A[i - 2] + P[i - 2]) % mod) % mod;
			}
			if (i > 2) {
				A[i] = ((A[i - 1] + A[i - 2]) % mod + A[i - 3]) % mod;
			}
		}
		return ((A[n - 1] % mod + P[n - 1] % mod) % mod + L[n - 1] % mod) % mod;
	}
}
