package org.wshuai.leetcode;

/**
 * Created by Wei on 08/06/2025.
 * #3477 https://leetcode.com/problems/fruits-into-baskets-ii/
 */
public class FruitsIntoBasketsII {

    // time O(m * n), space O(1)
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int m = fruits.length, n = baskets.length, res = m;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (baskets[j] >= fruits[i]) {
                    res--;
                    baskets[j] = -1;
                    break;
                }
            }
        }
        return res;
    }
}
