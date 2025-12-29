package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 12/07/2025.
 * #2061 https://leetcode.com/problems/number-of-spaces-cleaning-robot-cleaned/
 */
public class NumberOfSpacesCleaningRobotCleaned {

    private static final int[][] DIRS = new int[][]{
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public int numberOfCleanRooms(int[][] room) {
        int m = room.length, n = room[0].length;
        Set<Integer> cleaned = new HashSet<>(), visited = new HashSet<>();
        dfs(room, 0, 0, 0, visited, cleaned);
        return cleaned.size();
    }

    private void dfs(int[][] room, int x, int y, int d, Set<Integer> visited, Set<Integer> cleaned) {
        int code = encode(x, y, d);
        if (visited.contains(code)) {
            return;
        }
        visited.add(code);
        cleaned.add((x << 10) + y);
        int r = x + DIRS[d][0], c = y + DIRS[d][1];
        if (r < 0 || r >= room.length || c < 0 || c >= room[0].length || room[r][c] == 1) {
            // 下一个步的位置不可到达，将当前方向顺时针旋转90度
            dfs(room, x, y, (++d) % 4, visited, cleaned);
        } else {
            // 下一个步的位置可以到达
            dfs(room, r, c, d, visited, cleaned);
        }
    }

    private int encode(int x, int y, int d) {
        // 机器人有三个状态，当前位置的x值，y值以及方向值d。因为本题x和y的最大值是300
        // 而方向码只有四种。可以把三个状态压缩到一个32位整型中。
        int res = (x << 10) + y;
        res = (res << 3) + d;
        return res;
    }
}
