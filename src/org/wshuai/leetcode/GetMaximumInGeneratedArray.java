package org.wshuai.leetcode;

/**
 * Created by Wei on 11/09/2020.
 * #1644 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 */
public class GetMaximumInGeneratedArray {

    // time O(n), space O(n)
    public int getMaximumGenerated(int n) {
        if(n == 0){
            return 0;
        }
        if(n <= 2){
            return 1;
        }
        int max = 0;
        int[] res = new int[n + 1];
        res[1] = 1;
        for(int i = 1; ; i++){
            int a = (i << 1), b = a + 1;
            if(a > n || b > n){
                break;
            }
            res[a] = res[i];
            res[b] = res[i] + res[i + 1];
            max = Math.max(max, res[b]);
        }
        return max;
    }
}
