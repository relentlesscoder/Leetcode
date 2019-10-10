package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.WalkingRobotSimulation;

public class WalkingRobotSimulationTest {
	@Test
	public void testcase1() {
		int[][] ob = new int[1][2];
		ob[0] = new int[]{2, 4};
		WalkingRobotSimulation wr = new WalkingRobotSimulation();
		int p = wr.robotSim(new int[]{4, -1, 4, -2, 4}, ob);
	}

	@Test
	public void testcase2() {
		int[][] ob = new int[0][0];
		WalkingRobotSimulation wr = new WalkingRobotSimulation();
		int p = wr.robotSim(new int[]{4, -1, 3}, ob);
	}

	@Test
	public void testcase3() {
		int[][] ob = new int[10][2];
		ob[0] = new int[]{-4, -1};
		ob[1] = new int[]{1, -1};
		ob[2] = new int[]{1, 4};
		ob[3] = new int[]{5, 0};
		ob[4] = new int[]{4, 5};
		ob[5] = new int[]{-2, -1};
		ob[6] = new int[]{2, -5};
		ob[7] = new int[]{5, 1};
		ob[8] = new int[]{-3, -1};
		ob[9] = new int[]{5, -3};
		WalkingRobotSimulation wr = new WalkingRobotSimulation();
		int p = wr.robotSim(new int[]{-2, 8, 3, 7, -1}, ob);
	}
}
