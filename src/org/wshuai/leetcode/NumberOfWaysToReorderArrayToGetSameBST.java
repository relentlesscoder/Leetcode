package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/21/2020.
 * #1569 https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/
 */
public class NumberOfWaysToReorderArrayToGetSameBST {

    private static final long MOD = 1_000_000_007;

    // time O(n^2), space O(n^2)
    // https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/discuss/819725/Java-Clean-code-uses-Yang-Hui'sPascal's-Triangle-With-Explanation
    // https://zhuanlan.zhihu.com/p/74787475
    // https://zhuanlan.zhihu.com/p/41855459
    public int numOfWays(int[] nums) {
        int len = nums.length;
        List<Integer> arr = new ArrayList<>();
        for(int num : nums){
            arr.add(num);
        }
        return (int)getCombs(arr, getTriangle(len + 1)) - 1;
    }

    private long getCombs(List<Integer> nums, long[][] combs){
        if(nums.size() <= 2){
            return 1;
        }
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        for(int n : nums){
            if(n < root){
                left.add(n);
            }else if (n > root){
                right.add(n);
            }
        }
        return (combs[left.size() + right.size()][left.size()] * (getCombs(left, combs) % MOD) % MOD) * getCombs(right, combs) % MOD;
    }

    // Pascal's Triangle
    private long[][] getTriangle(int n){
        long[][] triangle = new long[n][n];
        for(int i = 0; i < n; i++){
            triangle[i][0] = triangle[i][i] = 1;
        }
        for(int i = 2; i < n; i++){
            for(int j = 1; j < i; j++){
                triangle[i][j] = (triangle[i - 1][j - 1] + triangle[i - 1][j]) % MOD;
            }
        }
        return triangle;
    }
}
