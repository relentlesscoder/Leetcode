package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/16/2019.
 * #0853 https://leetcode.com/problems/car-fleet/
 */
public class CarFleet {

	// time O(n), space O(n)
    public int carFleetLTR(int target, int[] position, int[] speed) {
        int n = position.length;
        double[] time = new double[target];
        for (int i = 0; i < n; i++) {
			// Calculate time it needs to arrive target for each car
            time[position[i]] = 1.0 * (target - position[i]) / speed[i];
        }
        Deque<Integer> stack = new ArrayDeque<>();
		// Iterate by the starting position
        for (int i = 0; i < target; i++) {
            if (time[i] == 0) {
                continue;
            }
			// If a car j at i's left can arrive target with time <= time[i],
			// it will catch up with i to form a group
            while (!stack.isEmpty() && time[stack.peek()] <= time[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        return stack.size();
    }

	// time O(n), space O(n)
	public int carFleetRTL(int target, int[] position, int[] speed) {
		int n = position.length;
		double[] time = new double[target];
		for (int i = 0; i < n; i++) {
			time[position[i]] = 1.0 * (target - position[i]) / speed[i];
		}
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = target - 1; i >= 0; i--) {
			if (time[i] == 0) {
				continue;
			}
			/*
			if (!stack.isEmpty() && time[stack.peek()] >= time[i]) {
				continue;
			} else {
				stack.push(i);
			}*/
			if (stack.isEmpty() || time[stack.peek()] < time[i]) {
				stack.push(i);
			}
		}
		return stack.size();
	}
}
