package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #2391 https://leetcode.com/problems/minimum-amount-of-time-to-collect-garbage/
 */
public class MinimumAmountOfTimeToCollectGarbage {

    // time O(n), space O(n)
    public int garbageCollection(String[] garbage, int[] travel) {
        int res = 0;
        int[] lastIndex = new int[26], distance = new int[garbage.length];
        for (int i = 0; i < garbage.length; i++) {
            res += garbage[i].length(); // adds time to collect all garbage
            for (char c : garbage[i].toCharArray()) {
                lastIndex[c - 'A'] = i + 1; // find the last garbage for each type
            }
        }
        for (int i = 1; i < garbage.length; i++) {
            distance[i] = distance[i - 1] + travel[i - 1];
        }
        for (char c : "MPG".toCharArray()) {
            int last = lastIndex[c - 'A'];
            res += last == 0 ? 0 : distance[last - 1]; // adds time to travel from house 0 to the last house that has garbage for each type
        }
        return res;
    }
}
