package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 09/11/2019.
 * #0489 https://leetcode.com/problems/robot-room-cleaner/
 */
public class RobotRoomCleaner {
	private int[] dirs;
	private Set<List<Integer>> visited;
	private Robot robot;

	public void cleanRoom(Robot robot) {
		this.robot = robot;
		dirs = new int[]{-1, 0, 1, 0, -1};
		visited = new HashSet<>();
		dfs(0, 0, 0);
	}

	private void dfs(int r, int c, int d) {
		robot.clean();
		List<Integer> cur = Arrays.asList(r, c);
		visited.add(cur);
		for (int i = 0; i < 4; i++) {
			int z = (d + i) % 4, x = r + dirs[z], y = c + dirs[z + 1];
			List<Integer> next = Arrays.asList(x, y);
			if (!visited.contains(next) && robot.move()) {
				dfs(x, y, z);
				reset();
			}
			robot.turnRight();
		}
	}

	public void reset() {
		robot.turnRight();
		robot.turnRight();
		robot.move();
		robot.turnRight();
		robot.turnRight();
	}
}


// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
	// Returns true if the cell in front is open and robot moves into the cell.
	// Returns false if the cell in front is blocked and robot stays in the current cell.
	public boolean move();

	// Robot will stay in the same cell after calling turnLeft/turnRight.
	// Each turn will be 90 degrees.
	public void turnLeft();

	public void turnRight();

	// Clean the current cell.
	public void clean();
}
