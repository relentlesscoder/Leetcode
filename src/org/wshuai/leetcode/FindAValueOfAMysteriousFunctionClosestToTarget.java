package org.wshuai.leetcode;

/**
 * Created by Wei on 07/29/2020.
 * #1521 https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/
 */
public class FindAValueOfAMysteriousFunctionClosestToTarget {

    // time O(n)
    public int closestToTarget(int[] arr, int target) {
        int res = Integer.MAX_VALUE, n = arr.length, cur = 0;
        int[] bitCount = new int[32];
        for(int l = 0, r = 0; r < n; r++){
            // bitwise AND will only make the sliding window
            // "sum" less or equal.
            // for the current left, we extending the sliding
            // window as far as we could until we see a sum less
            // than target.
            updateBitCount(arr[r], bitCount, 1);
            cur = bitCountToInt(bitCount, r - l + 1);
            res = Math.min(res, Math.abs(cur - target));
            // once we see the current sum is less than the target,
            // we should not further extending the current window
            while(l <= r && cur < target){
                updateBitCount(arr[l], bitCount, -1);
                l++;
                cur = bitCountToInt(bitCount, r - l + 1);
                res = Math.min(res, Math.abs(cur - target));
            }
        }
        return res;
    }

    private void updateBitCount(int num, int[] bitCount, int update){
        for(int i = 0; i <= 31; i++){
            if(((num >> i) & 1) == 1){
                bitCount[i] += update;
            }
        }
    }

    private int bitCountToInt(int[] bitCount, int threshold){
        int res = 0;
        for(int i = 0; i <= 31; i++){
            if(bitCount[i] > 0 && bitCount[i] == threshold){
                res |= (1 << i);
            }
        }
        return res;
    }
}
