package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Wei on 12/17/2019.
 * #1183 https://leetcode.com/problems/maximum-number-of-ones/
 */
public class MaximumNumberOfOnes {
	// https://leetcode.com/problems/maximum-number-of-ones/discuss/377099/C%2B%2B-solution-with-explanation
	public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
		List<Integer> seq = new ArrayList<>();
		for(int i = 0; i < sideLength; i++){
			for(int j = 0; j < sideLength; j++){
				//RoundUp((width - i)/sideLength)) * RoundUp((height - j)/sideLength))
				int cnt = ((width - i + sideLength - 1)/sideLength) * ((height - j + sideLength - 1)/sideLength);
				seq.add(cnt);
			}
		}
		Collections.sort(seq, Collections.reverseOrder());
		int ans = 0;
		for(int i = 0; i < maxOnes; i++){
			ans += seq.get(i);
		}
		return ans;
	}
}
