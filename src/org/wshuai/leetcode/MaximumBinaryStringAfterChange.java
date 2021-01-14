package org.wshuai.leetcode;

/**
 * Created by Wei on 01/14/2021.
 * #1702 https://leetcode.com/problems/maximum-binary-string-after-change/
 */
public class MaximumBinaryStringAfterChange {

    // time O(n)
    public String maximumBinaryString(String binary) {
        int i = 0, n = binary.length(), size = n, zeros = 0;
        char[] res = new char[n];
        while(i < n && binary.charAt(i) == '1'){ // leading 1s will not be moved
            res[i++] = '1';
            size--;
        }
        for(int k = i; k < n; k++){
            if(binary.charAt(k) == '0'){
                zeros++;
            }
        }
        // since:
        // op1: 000...000 -> 111...110
        // op2: 111...000 -> 000...111
        // thus simply move all the 0s to the front and do operation 1
        int leading = zeros - 1;
        int trailing = size - zeros;
        if(leading >= 0){
            for(int j = 0; j < leading; j++){
                res[i++] = '1';
            }
            res[i++] = '0';
        }
        for(int j = 0; j < trailing; j++){
            res[i++] = '1';
        }
        return String.valueOf(res);
    }
}
