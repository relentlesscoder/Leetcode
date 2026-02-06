package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 02/04/2026.
 * #LCP51 https://leetcode.cn/problems/UEcfPD/
 */
public class LCP51 {

    private int res = -1;

    // time O(m * 2^n), space O(n)
    public int perfectMenuBacktracking(int[] materials, int[][] cookbooks,
                                       int[][] attribute, int limit) {
        res = -1;
        dfs(0, 0, 0, materials, cookbooks, attribute, limit);
        return res;
    }

    private void dfs(int i, int x, int y, int[] materials,
                     int[][] cookbooks, int[][] attribute, int limit) {
        // i 是 cookbooks 中当前准备制作的烹饪的料理
        // x 是当前的饱腹感
        // y 是当前的好感度
        if (i == cookbooks.length) {
            if (x >= limit) { // 饱腹感不小于 limit
                res = Math.max(res, y); // 好感度的最大值
            }
            return;
        }
        // 先 DFS 不制作当前料理的情况
        dfs(i + 1, x, y, materials, cookbooks, attribute, limit);
        boolean doable = true; // 材料够不够制作
        // 按照配料表扣除制作当前料理所需食材的数量，注意不管够不够我们都先扣除。这样可以减少一次循环。
        for (int j = 0; j < cookbooks[i].length; j++) {
            materials[j] -= cookbooks[i][j];
            if (materials[j] < 0) { // 食材不够
                doable = false;
            }
        }
        if (doable) { // 如果食材足够则 DFS 制作当前料理的情况
            dfs(i + 1, x + attribute[i][1], y + attribute[i][0],
                    materials, cookbooks, attribute, limit);
        }
        // 回溯完复原现场
        for (int j = 0; j < cookbooks[i].length; j++) {
            materials[j] += cookbooks[i][j];
        }
    }

    // time O(m * 2^n), space O(m)
    public int perfectMenuBitMask(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        int res = -1, n = cookbooks.length, m = 1 << n;
        for (int i = 0; i < m; i++) {
            int[] copy = new int[materials.length];
            Arrays.setAll(copy, a -> materials[a]);
            int x = 0, y = 0;
            boolean doable = true;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) > 0) {
                    for (int k = 0; k < cookbooks[j].length; k++) {
                        if (copy[k] < cookbooks[j][k]) {
                            doable = false;
                            break;
                        }
                    }
                    if (doable) {
                        for (int k = 0; k < cookbooks[j].length; k++) {
                            copy[k] -= cookbooks[j][k];
                        }
                        x += attribute[j][1];
                        y += attribute[j][0];
                    }
                }
            }
            if (!doable) {
                continue;
            }
            if (x >= limit) {
                res = Math.max(res, y);
            }
        }
        return res;
    }
}
