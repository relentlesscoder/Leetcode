package org.wshuai.leetcode;

/**
 * Created by Wei on 11/25/2023.
 * #2120 https://leetcode.com/problems/execution-of-all-suffix-instructions-staying-in-a-grid/
 */
public class ExecutionOfAllSuffixInstructionsStayingInAGrid {

    // time O(m^2), space O(m)
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int m = s.length();
        int[] res = new int[m];
        char[] dirs = s.toCharArray();
        for (int i = 0; i < m; i++) {
            res[i] = dfs(n, startPos[0], startPos[1], i, dirs);
        }
        return res;
    }

    private int dfs(int n, int x, int y, int i, char[] dirs) {
        if (i >= dirs.length) {
            return 0;
        }
        int a = x, b = y;
        if (dirs[i] == 'L') {
            b--;
        } else if (dirs[i] == 'R') {
            b++;
        } else if (dirs[i] == 'U') {
            a--;
        } else {
            a++;
        }
        if (a < 0 || a >= n || b < 0 || b >= n) {
            return 0;
        }
        return 1 + dfs(n, a, b, i + 1, dirs);
    }
}
