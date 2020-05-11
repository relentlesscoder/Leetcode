package org.wshuai.leetcode;

/**
 * Created by Wei on 05/11/2020.
 * #1442 https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {

    // time O(n^2)
    public int countTriplets(int[] arr) {
        int res = 0, n = arr.length;
        int[] prefix = new int[n + 1];
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
        }
        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){
                // check if subarray [i ... j] has XOR 0
                if((prefix[j + 1] ^ prefix[i]) == 0){
                    // each number between [i + 1, j] can be
                    // a dividing point
                    res += (j - i);
                }
            }
        }
        return res;
    }
}
