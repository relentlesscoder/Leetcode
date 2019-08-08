package org.wshuai.leetcode;

/**
 * Created by Wei on 8/8/19.
 * #905 https://leetcode.com/problems/sort-array-by-parity/
 */
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = A.length-1;
        while(left < right){
            if(A[left] % 2 == 0){
                left++;
            }else{
                while(right > left && A[right] % 2 == 1){
                    right--;
                }
                if(A[right] % 2 == 0){
                    int tmp = A[left];
                    A[left] = A[right];
                    A[right] = tmp;
                    left++;
                    right--;
                }
            }
        }
        return A;
    }
}
