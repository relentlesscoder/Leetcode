package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/07/2019.
 * #1040 https://leetcode.com/problems/moving-stones-until-consecutive-ii/
 */
public class MovingStonesUntilConsecutiveII {

    // time O(n), space O(1)
    public int[] numMovesStonesII(int[] stones) {
		// https://leetcode.cn/problems/moving-stones-until-consecutive-ii/solutions/2212638/tu-jie-xia-tiao-qi-pythonjavacgo-by-endl-r1eb/
        Arrays.sort(stones);
        int n = stones.length;
        int e1 = stones[n - 2] - stones[0] - n + 2;
        int e2 = stones[n - 1] - stones[1] - n + 2;
        int maxMove = Math.max(e1, e2);
        if (e1 == 0 || e2 == 0) {
            return new int[]{Math.min(2, maxMove), maxMove};
        }
        int maxCount = 0;
        for (int right = 0, left = 0; right < n; right++) {
            while (stones[right] - stones[left] + 1 > n) {
                left++;
            }
            maxCount = Math.max(maxCount, right - left + 1);
        }
        return new int[]{n - maxCount, maxMove};
    }
}
