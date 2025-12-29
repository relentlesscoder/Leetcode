package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/08/2025.
 * #LCP63 https://leetcode.cn/problems/EXvqDp/
 */
public class LCP63 {

    private static final int[] DIRS = new int[]{-1, 0, 1, 0, -1};

    // time O(m * n), space O(1)
    public int[][] ballGame(int num, String[] plate) {
        // 注意起步所在的格子不计入步数
        List<int[]> balls = new ArrayList<>();
        int m = plate.length, n = plate[0].length();
        for (int i = 1; i < m - 1; i++) {
            if (plate[i].charAt(0) == '.' && dfs(plate, i, 0, 1, num)) {
                balls.add(new int[]{i, 0});
            }
            if (plate[i].charAt(n - 1) == '.' && dfs(plate, i, n - 1, 3, num)) {
                balls.add(new int[]{i, n - 1});
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (plate[0].charAt(i) == '.' && dfs(plate, 0, i, 2, num)) {
                balls.add(new int[]{0, i});
            }
            if (plate[m - 1].charAt(i) == '.' && dfs(plate, m - 1, i, 0, num)) {
                balls.add(new int[]{m - 1, i});
            }
        }
        int[][] res = new int[balls.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i] = balls.get(i);
        }
        return res;
    }

    private boolean dfs(String[] plate, int i, int j, int d, int steps) {
        if (i < 0 || i >= plate.length || j < 0 || j >= plate[0].length() || steps < 0) {
            return false;
        }
        char cell = plate[i].charAt(j);
        if (cell == 'O') {
            return true;
        } else if (cell == 'E') {
            d = (d + 1) % 4;
        } else if (cell == 'W') {
            d = ((d - 1) % 4 + 4) % 4;
        }
        int x = i + DIRS[d], y = j + DIRS[d + 1];
        return dfs(plate, x, y, d, steps - 1);
    }
}
