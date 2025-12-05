package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 11/25/2019.
 * #1124 https://leetcode.com/problems/longest-well-performing-interval/
 */
public class LongestWellPerformingInterval {

	// time O(n), space O(n)
	public int longestWPIMonotonicStack(int[] hours) {
		// Input:      1,  1, -1, -1, -1, -1,  1
		// Prefix:  0, 1,  2,  1,  0, -1, -2, -1
		// Stack:   0, x,  x,  x,  x,  5,  6,  x
		int res = 0, n = hours.length;
		int[] scores = new int[n + 1];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(0);
		for (int i = 1; i <= n; i++) {
			scores[i] = scores[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
			if (scores[stack.peek()] > scores[i]) {
				stack.push(i);
			}
		}
		for (int i = n; i > 0; i--) {
			while (!stack.isEmpty() && scores[stack.peek()] < scores[i]) {
				res = Math.max(res, i - stack.pop());
			}
		}
		return res;
	}

    // time O(n), space O(n)
    public int longestWPIPrefixSum(int[] hours) {
        int res = 0, n = hours.length, score = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            score += hours[i] > 8 ? 1 : -1;
            // If current index i has prefix score > 0, then the entire
            // prefix [0, i] is a WPI obviously it will be the longest
            // WPI so far.
            if (score > 0) {
                res = i + 1;
            } else if (map.containsKey(score - 1)) {
                // Or we can find if there is index j with prefix = score - 1,
                // the subarray [j + 1, i] is a candidate
                // e.g.  [1, 1,-1,-1,-1,-1, 1, 1,-1,-1]
                //       [1, 2, 1, 0,-1,-2,-1, 0,-1,-2]
                // Subarray [6,8]
                res = Math.max(res, i - map.get(score - 1));
            }
            // Note that we only update index for score when there is no entry
            // in the map since we need the smallest index to form longest WPI
            map.putIfAbsent(score, i);
        }
        return res;
    }
}
