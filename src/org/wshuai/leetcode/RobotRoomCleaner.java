package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/11/2019.
 * #0489 https://leetcode.com/problems/robot-room-cleaner/
 */
public class RobotRoomCleaner {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    public void cleanRoom(Robot robot) {
        // 图解 https://leetcode.cn/problems/robot-room-cleaner/solutions/42959/sao-di-ji-qi-ren-by-leetcode/
        Set<Integer> visited = new HashSet<>();
        dfs(0, 0, 0, robot, visited);
    }

    private void dfs(int x, int y, int d, Robot robot, Set<Integer> visited) {
        // 打扫当前位置
        robot.clean();
        // 标记当前位置为已打扫，这里用一个32位整型来压缩机器人的位置
        visited.add((x << 10) + y);
        for (int i = 0; i < 4; i++) {
            // 计算下一步的位置和方向
            int k = (d + i) % 4, r = x + DIRS[k], c = y + DIRS[k + 1];
            // 如果下一步可行，继续深度搜索下一个位置
            if (!visited.contains((r << 10) + c) && robot.move()) {
                dfs(r, c, k, robot, visited);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            // 机器人每次右转正好对应方向数组DIRS里面的下一个方向
            robot.turnRight();
        }
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
