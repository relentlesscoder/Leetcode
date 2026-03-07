package org.wshuai.leetcode.backtracking;

/**
 * Created by Wei on 10/28/2023.
 * #1718 https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 */
public class ConstructTheLexicographicallyLargestValidSequence {

    // time O(n!), space O(n)
    public int[] constructDistancedSequence(int n) {
        int m = 2 * n - 1;
        int[] res = new int[m];
        dfs(0, new boolean[n + 1], res);
        return res;
    }

    private boolean dfs(int i, boolean[] used, int[] res) {
        int m = res.length, n = used.length - 1;
        if (i == m) { // 所有数字都填完了
            return true;
        }
        if (res[i] != 0) { // 如果当前位置已经被前面的操作填好了则跳过该位置
            return dfs(i + 1, used, res);
        }
        for (int j = n; j >= 1; j--) { // 倒序找最大的数字
            if (used[j]) { // 用过了
                continue;
            }
            used[j] = true;
            res[i] = j;
            if (j == 1) { // 如果数字是 1
                if (dfs(i + 1, used, res)) {
                    return true;
                }
            } else if (i + j < m && res[i + j] == 0) { // 如果数字大于 1 且里当前位置距离为 i 的位置为空
                res[i + j] = j;
                if (dfs(i + 1, used, res)) {
                    return true;
                }
                // 恢复现场
                res[i + j] = 0;
            }
            // 恢复现场
            res[i] = 0;
            used[j] = false;
        }
        return false;
    }
}
