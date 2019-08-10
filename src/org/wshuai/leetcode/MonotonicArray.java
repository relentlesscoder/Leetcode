package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/9/19.
 * #896 https://leetcode.com/problems/monotonic-array/
 */
public class MonotonicArray {
    public boolean isMonotonic(int[] A) {
        if(A.length <= 1){
            return true;
        }
        Set<Boolean> set = new HashSet<Boolean>();
        for(int i = 1; i < A.length; i++){
            if(A[i] == A[i-1]){
                continue;
            }
            set.add(A[i] > A[i-1]);
        }
        return set.size() < 2;
    }
}
