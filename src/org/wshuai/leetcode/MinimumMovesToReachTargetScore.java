package org.wshuai.leetcode;

/**
 * Created by Wei on 09/13/2023.
 * #2139 https://leetcode.com/problems/minimum-moves-to-reach-target-score/
 */
public class MinimumMovesToReachTargetScore {

    // time O(n + log(n)), space O(1)
    public int minMoves(int target, int maxDoubles) {
        int res = 0;
        while (target > 1 && maxDoubles-- > 0) {
            res += 1 + (target % 2);
            target = target >> 1;
        }
        return res + target - 1;
    }
}
