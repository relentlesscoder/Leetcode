package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3100 https://leetcode.com/problems/water-bottles-ii/
 */
public class WaterBottlesII {

    // time O(1), space O(1)
    public int maxBottlesDrunkMath(int n, int e) {
        // https://leetcode.cn/problems/water-bottles-ii/solutions/2716773/an-ti-yi-mo-ni-jian-ji-xie-fa-pythonjava-n6g7/
        int b = e * 2 - 1;
        int k = ((int) Math.sqrt(b * b + (n - e) * 8) - b + 2) / 2;
        return n + k;
    }

    // time O(sqrt(n)), space O(1)
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res = 0;
        while (numBottles >= numExchange) {
            res += numExchange;
            numBottles -= numExchange - 1;
            numExchange++;
        }
        return res + numBottles;
    }
}
