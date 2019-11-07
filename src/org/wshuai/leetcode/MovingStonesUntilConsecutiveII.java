package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/7/19.
 * #1040 https://leetcode.com/problems/moving-stones-until-consecutive-ii/
 */
public class MovingStonesUntilConsecutiveII {
	// good explanation https://leetcode.com/problems/moving-stones-until-consecutive-ii/discuss/289357/c%2B%2B-with-picture
	public int[] numMovesStonesII(int[] stones) {
		Arrays.sort(stones);
		int i = 0;
		int N = stones.length;
		int low = N;
		int high = Math.max(stones[N - 1] - stones[1] - N + 2,
				stones[N - 2] - stones[0] - N + 2);
		for(int j = 0; j < N; j++){
			// current window size -> stones[j] - stones[i] + 1
			// number of stones stored in the current window -> j - i + 1
			while(stones[j] - stones[i] + 1 > N){
				i++;
			}
			if(j - i + 1 == N - 1 && stones[j] - stones[i] == N - 2){
				low = Math.min(low, 2);
			}else{
				low = Math.min(low, N - (j - i + 1));
			}
		}
		return new int[]{low, high};
	}
}
