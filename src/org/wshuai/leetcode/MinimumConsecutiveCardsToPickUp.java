package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wei on 08/06/2025.
 * #2260 https://leetcode.com/problems/minimum-consecutive-cards-to-pick-up/
 */
public class MinimumConsecutiveCardsToPickUp {

    // time O(n), space O(n)
    public int minimumCardPickup(int[] cards) {
        int n = cards.length, res = n + 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(cards[i])) {
                res = Math.min(res, i - map.get(cards[i]) + 1);
            }
            map.put(cards[i], i);
        }
        return res > n ? -1 : res;
    }
}
