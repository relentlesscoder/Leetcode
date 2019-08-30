package org.wshuai.leetcode;

import java.util.TreeSet;

/**
 * Created by Wei on 8/26/19.
 * #363 https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 */
public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int r = matrix.length;
        int c = matrix[0].length;

        int[][] sum = new int[r][c+1];
        for(int i = 0; i < r; i++){
            for(int j = 1; j < c+1; j++){
                sum[i][j] = sum[i][j-1] + matrix[i][j-1];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < c; i++){
            for(int j = i; j < c; j++){
                int[] aux = new int[r];
                for(int y = 0; y < r; y++){
                    aux[y] = sum[y][j+1]-sum[y][i];
                }
                max = Math.max(findMax(aux, k), max);
            }
        }
        return max;
    }

    private int findMax(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        TreeSet<Integer> s = new TreeSet();
        s.add(0);

        for(int i = 0;i < nums.length; i ++){
            int t = sum + nums[i];
            sum = t;
            Integer gap = s.ceiling(sum - k);
            if(gap != null) max = Math.max(max, sum - gap);
            s.add(t);
        }

        return max;
    }
}
