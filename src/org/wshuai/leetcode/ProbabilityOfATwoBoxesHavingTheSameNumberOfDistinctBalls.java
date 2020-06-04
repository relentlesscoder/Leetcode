package org.wshuai.leetcode;

/**
 * Created by Wei on 06/02/2020.
 * #1467 https://leetcode.com/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls/
 */
public class ProbabilityOfATwoBoxesHavingTheSameNumberOfDistinctBalls {

    private double valid;

    public double getProbability(int[] balls) {
        valid = 0.0;
        int size = 0, k = 0;
        for(int b : balls){
            size += b;
        }
        k = size / 2;
        long[][] binom = new long[size + 1][k + 1];
        binom[0][0] = 1;
        for(int i = 1; i <= size; i++){
            binom[i][0] = 1;
            for(int j = 1; j <= k; j++){
                binom[i][j] = binom[i - 1][j] + binom[i - 1][j - 1];
            }
        }
        dfs(balls, 0, k, 0, 0, 0, 0, 1.0, binom);
        return valid / binom[size][k];
    }

    private void dfs(int[] balls, int i, int k, int num1, int num2,
                     int dist1, int dist2, double factor, long[][] binom){
        if(num1 > k || num2 > k){
            return;
        }
        if(i == balls.length && (num1 == k || num2 == k)){
            valid += (dist1 == dist2 ? factor : 0.0);
            return;
        }
        for(int j = 0; j <= Math.min(balls[i], k - num1); j++){
            dfs(balls, i + 1, k, num1 + j, num2 + balls[i] - j,
                    dist1 + (j == 0 ? 0 : 1), dist2 + (j == balls[i] ? 0 : 1),
                    factor * binom[balls[i]][j], binom);
        }
    }
}
