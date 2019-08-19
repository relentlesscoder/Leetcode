package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #985 https://leetcode.com/problems/sum-of-even-numbers-after-queries/
 */
public class SumOfEvenNumbersAfterQueries {
    public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int sum = 0;
        for(int i: A){
            if(i % 2 == 0){
                sum += i;
            }
        }
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int val = queries[i][0];
            int index = queries[i][1];
            int v1 = A[index];
            int v2 = A[index] + val;
            if(v1 % 2 == 0){
                if(v2 % 2 == 0){
                    sum += (v2 - v1);
                }else{
                    sum -= v1;
                }
            }else{
                if(v2 % 2 == 0){
                    sum += v2;
                }
            }
            A[index] = v2;
            res[i] = sum;
        }
        return res;
    }
}
