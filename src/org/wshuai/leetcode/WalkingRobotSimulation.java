package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/31/2019.
 * #874 https://leetcode.com/problems/walking-robot-simulation/
 */
public class WalkingRobotSimulation {
	public int robotSim(int[] commands, int[][] obstacles) {
		int max = 0;
		Set<String> set = new HashSet<>();
		for (int i = 0; i < obstacles.length; i++) {
			set.add(obstacles[i][0] + "#" + obstacles[i][1]);
		}
		int[] curr = new int[2];
		int[][] move = new int[4][2];
		move[0] = new int[]{0, 1};
		move[1] = new int[]{1, 0};
		move[2] = new int[]{0, -1};
		move[3] = new int[]{-1, 0};
		int cm = 0;
		for (int command : commands) {
			if (command == -1) {
				cm = cm == 3 ? 0 : (cm + 1);
			} else if (command == -2) {
				cm = cm == 0 ? 3 : (cm - 1);
			} else if (command >= 1 && command <= 9) {
				while (command > 0) {
					int x = curr[0] + move[cm][0];
					int y = curr[1] + move[cm][1];
					if (!set.contains(x + "#" + y)) {
						curr[0] = x;
						curr[1] = y;
						int ax = x > 0 ? x - 0 : 0 - x;
						int ay = y > 0 ? y - 0 : 0 - y;
						int as = ax * ax + ay * ay;
						if (as > max) {
							max = as;
						}
					}
					command--;
				}
			}
		}
		return max;
	}
}
