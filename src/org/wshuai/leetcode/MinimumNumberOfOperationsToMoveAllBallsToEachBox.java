package org.wshuai.leetcode;

/**
 * Created by Wei on 02/25/2021.
 * #1769 https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 */
public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {

    // time O(n)
    public int[] minOperations(String boxes) {
        int n = boxes.length(), leftBalls = 0, rightBalls = 0, leftMoves = 0, rightMoves = 0;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            leftMoves += leftBalls;
            res[i] += leftMoves;
            leftBalls += boxes.charAt(i) == '1' ? 1 : 0;
        }
        for(int i = n - 1; i >= 0; i--){
            rightMoves += rightBalls;
            res[i] += rightMoves;
            rightBalls += boxes.charAt(i) == '1' ? 1 : 0;
        }
        return res;
    }
}
