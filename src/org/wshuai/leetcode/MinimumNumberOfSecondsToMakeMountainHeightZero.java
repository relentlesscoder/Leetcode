package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2026.
 * #3296 https://leetcode.com/problems/minimum-number-of-seconds-to-make-mountain-height-zero/
 */
public class MinimumNumberOfSecondsToMakeMountainHeightZero {

    // time O(h * log(n)), space O(n)
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // 循环 mountainHeight 次，每次选一个工作后总用时最短的工人，把山的高度降低 1 。
        long res = 0;
        int n = workerTimes.length;
        long[][] times = new long[n][3];
        for (int i = 0; i < n; i++) {
            times[i] = new long[] {workerTimes[i], workerTimes[i], workerTimes[i]};
        }
        for (int i = n / 2 - 1; i >= 0; i--) {
            sink(times, i);
        }
        while (mountainHeight-- > 0) {
            long[] curr = times[0];
            res = curr[2];
            curr[0] += curr[1];
            curr[2] += curr[0];
            sink(times, 0);
        }
        return res;
    }

    private void sink(long[][] nums, int i) {
        int n = nums.length;
        while (2L * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && nums[j + 1][2] < nums[j][2]) {
                j++;
            }
            if (nums[j][2] >= nums[i][2]) {
                break;
            }
            long[] temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i = j;
        }
    }
}
