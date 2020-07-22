package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Wei on 07/22/2020.
 * #1504 https://leetcode.com/problems/count-submatrices-with-all-ones/
 */
public class CountSubmatricesWithAllOnes {

    // time O(m*n), space O(m*n)
    // https://leetcode.com/problems/count-submatrices-with-all-ones/discuss/720265/Java-Detailed-Explanation-From-O(MNM)-to-O(MN)-by-using-Stack
    public int numSubmatMonotonicStack(int[][] mat) {
        int res = 0, m = mat.length, n = mat[0].length;
        int[] h = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                h[j] = (mat[i][j] == 0 ? 0 : h[j] + 1);
            }
            res += countSubmatStack(h);
        }
        return res;
    }

    private int countSubmatStack(int[] arr){
        int res = 0, n = arr.length;
        int[] sum = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                sum[i] = arr[i] * (i + 1);
            }else{
                sum[i] = sum[stack.peek()];
                sum[i] += arr[i] * (i - stack.peek());
            }
            stack.push(i);
        }
        for(int s : sum){
            res += s;
        }
        return res;
    }

    // time O(n*m^2), space O(m*n)
    public int numSubmat(int[][] mat) {
        int res = 0, m = mat.length, n = mat[0].length;

        for(int i = 0; i < m; i++){
            // h[k] = 1 means from row [i] to row[j],
            // column[k] all equals to 1
            int[] h = new int[n];
            Arrays.fill(h, 1);
            for(int j = i; j < m; j++){
                for(int k = 0; k < n; k++){
                    h[k] &= mat[j][k];
                }
                // converted to one dimension array
                res += countSubmat(h);
            }
        }

        return res;
    }

    private int countSubmat(int[] arr){
        int res = 0, cur = 0;
        for(int i = 0; i < arr.length; i++){
            cur = arr[i] == 0 ? 0 : cur + 1;
            res += cur;
        }
        return res;
    }
}
