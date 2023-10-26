package org.wshuai.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Wei on 10/25/2023.
 * #2534 https://leetcode.com/problems/time-taken-to-cross-the-door/
 */
public class TimeTakenToCrossTheDoor {

	// time O(n), space O(n)
	public int[] timeTaken(int[] arrival, int[] state) {
		int n = arrival.length, lastUsedTime = -100, lastUsedFor = -1;
		int[] res = new int[n];
		Deque<Integer> enterQueue = new ArrayDeque<>(), exitQueue = new ArrayDeque<>();
		for (int currentTime = arrival[0], person = 0; currentTime <= arrival[n - 1] + n; currentTime++) {
			while (person < n && arrival[person] <= currentTime) {
				if (state[person] == 0) {
					enterQueue.offer(person);
				} else {
					exitQueue.offer(person);
				}
				person++;
			}
			if (enterQueue.isEmpty() && exitQueue.isEmpty()) {
				continue;
			}
			if (lastUsedTime != currentTime - 1 || lastUsedFor == 1) {
				if (!exitQueue.isEmpty()) {
					res[exitQueue.poll()] = currentTime;
					lastUsedFor = 1;
				} else {
					res[enterQueue.poll()] = currentTime;
					lastUsedFor = 0;
				}
			} else {
				if (!enterQueue.isEmpty()) {
					res[enterQueue.poll()] = currentTime;
					lastUsedFor = 0;
				} else {
					res[exitQueue.poll()] = currentTime;
					lastUsedFor = 1;
				}
			}
			lastUsedTime = currentTime;
		}
		return res;
	}
}
