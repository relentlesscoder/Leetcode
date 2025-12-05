package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 10/09/2019.
 * #0904 https://leetcode.com/problems/fruit-into-baskets/
 */
public class FruitIntoBaskets {

    // time O(n), space O(MAX)
    public int totalFruit(int[] fruits) {
        int res = 0, n = fruits.length;
        int[] freq = new int[100_001];
        for (int i = 0, j = 0, type = 0; i < n; i++) {
            if (freq[fruits[i]]++ == 0) {
                type++;
            }
            while (type > 2) {
                if (--freq[fruits[j++]] == 0) {
                    type--;
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // time O(n), space O(n)
    public int totalFruitHashMap(int[] fruits) {
        int res = 0, n = fruits.length;
        Map<Integer, Integer> types = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            types.merge(fruits[i], 1, Integer::sum);
            while (types.size() > 2) {
                int count = types.merge(fruits[j], -1, Integer::sum);
                if (count == 0) {
                    types.remove(fruits[j]);
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
