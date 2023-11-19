package org.wshuai.leetcode;

/**
 * Created by Wei on 09/01/2023.
 * #2214 https://leetcode.com/problems/minimum-health-to-beat-game/description/
 */
public class MinimumHealthToBeatGame {

    // time O(n), space O(1)
    public long minimumHealth(int[] damage, int armor) {
        long res = 0, sum = 0;
        int maxDamage = -1;
        for (int i = 0; i < damage.length; i++) {
            if (damage[i] > maxDamage) {
                maxDamage = damage[i];
            }
            sum += damage[i];
        }
        return sum - Math.min(armor, maxDamage) + 1;
    }
}
