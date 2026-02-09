package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 08/30/2019.
 * #1079 https://leetcode.com/problems/letter-tile-possibilities/
 */
public class LetterTilePossibilities {

    private int res = 0;

    public int numTilePossibilities(String tiles) {
        // 本质上是全排列 + 去重
        res = 0;
        char[] arr = tiles.toCharArray();
        Arrays.sort(arr);
        dfs(0, arr);
        return res - 1;
    }

    private void dfs(int used, char[] s) {
        res++; // 题目要求所有的中间结果也需要统计
        if (used == (1 << s.length) - 1) { // 所有索引全部用完
            return;
        }
        int visited = 0; // 递归树上本层已经用过的元素值
        for (int j = 0; j < s.length; j++) {
            // 该索引被用过或者值被用过
            if (((1 << j) & used) > 0 || ((1 << (s[j] - 'A')) & visited) > 0) {
                continue;
            }
            visited |= (1 << (s[j] - 'A'));
            used |= (1 << j);
            dfs(used, s);
            // 恢复现场
            // 注意 visited 不需要恢复
            used ^= (1 << j);
        }
    }
}
