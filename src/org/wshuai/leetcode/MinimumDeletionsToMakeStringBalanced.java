package org.wshuai.leetcode;

/**
 * Created by Wei on 01/16/2021.
 * #1653 https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/
 */
public class MinimumDeletionsToMakeStringBalanced {

    // time O(n)
    public int minimumDeletions(String s) {
        int res = 0, as = 0, bs = 0;
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'a'){
                as++;
            }
        }
        res = as;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 'a'){
                as--;
            }else{
                bs++;
            }
            res = Math.min(res, as + bs);
        }
        return res;
    }
}
