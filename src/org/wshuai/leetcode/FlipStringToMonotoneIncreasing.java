package org.wshuai.leetcode;

/**
 * Created by Wei on 10/30/2019.
 * #926 https://leetcode.com/problems/flip-string-to-monotone-increasing/
 */
public class FlipStringToMonotoneIncreasing {
	public int minFlipsMonoIncr(String S) {
		char[] arr = S.toCharArray();
		int N = S.length();

		int[] onesFromLeft = new int[N];
		onesFromLeft[0] = 0;
		int ofl = 0;
		for(int i = 1; i < N; i++){
			if(arr[i - 1] == '1'){
				ofl++;
			}
			onesFromLeft[i] = ofl;
		}

		int[] zerosFromRight = new int[N];
		zerosFromRight[N - 1] = 0;
		int zfr = 0;
		for(int i = N - 2; i >= 0; i--){
			if(arr[i + 1] == '0'){
				zfr++;
			}
			zerosFromRight[i] = zfr;
		}

		int res = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++){
			// swap all 1 to 0 on the left and all 0 to 1 on the right for all position
			res = Math.min(onesFromLeft[i] + zerosFromRight[i], res);
		}
		return res == Integer.MAX_VALUE ? 0 : res;
	}
}
