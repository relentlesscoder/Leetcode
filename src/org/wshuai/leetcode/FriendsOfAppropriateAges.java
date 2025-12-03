package org.wshuai.leetcode;

/**
 * Created by Wei on 11/10/2019.
 * #0825 https://leetcode.com/problems/friends-of-appropriate-ages/
 */
public class FriendsOfAppropriateAges {

    // time O(n), space O(MAX)
    public int numFriendRequests(int[] ages) {
        // x -> y
        // age[y] > 0.5 * age[x] + 7
        // 0.5 * age[x] + 7 < age[y] <= age[x]
        int[] freq = new int[121];
        for (int age : ages) {
            freq[age]++;
        }
        int res = 0;
        for (int x = 0, y = 0, count = 0; x < freq.length; x++) {
            count += freq[x];
            if (y * 2 <= x + 14) {
                count -= freq[y++];
            }
            if (count > 0) {
                res += freq[x] * count - freq[x];
            }
        }
        return res;
    }
}
