package org.wshuai.leetcode;

/**
 * Created by Wei on 11/08/2025.
 * #2132 https://leetcode.com/problems/stamping-the-grid/
 */
public class StampingTheGrid {

    // time O(m * n), space O(m * n)
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        // Illustration
        // https://leetcode.cn/problems/stamping-the-grid/solutions/1199642/wu-nao-zuo-fa-er-wei-qian-zhui-he-er-wei-zwiu/
        // https://leetcode.cn/discuss/post/3573466/tu-jie-er-wei-qian-zhui-he-fu-mo-ban-dai-s2ag/
        int m = grid.length, n = grid[0].length;
        int[][] prefix = new int[m + 1][n + 1];
        // Initialize 2D prefix sum array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i + 1][j + 1] = prefix[i + 1][j] + prefix[i][j + 1] - prefix[i][j] + grid[i][j];
            }
        }
        // Build 2D difference array
        int[][] diff = new int[m + 2][n + 2];
        // Lower right corner [i2 - 1, j2 - 1]
        for (int i2 = stampHeight; i2 <= m; i2++) {
            for (int j2 = stampWidth; j2 <= n; j2++) {
                // Upper left corner [i1 - 1, i2 - 1]
                int i1 = i2 - stampHeight + 1;
                int j1 = j2 - stampWidth + 1;
                if (prefix[i2][j2] - prefix[i2][j1 - 1] - prefix[i1 - 1][j2] + prefix[i1 - 1][j1 - 1] == 0) {
                    diff[i1][j1]++;
                    diff[i1][j2 + 1]--;
                    diff[i2 + 1][j1]--;
                    diff[i2 + 1][j2 + 1]++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
