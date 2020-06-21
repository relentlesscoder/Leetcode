package org.wshuai.leetcode;

/**
 * Created by Wei on 05/03/2020.
 * #1432 https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/
 */
public class MaxDifferenceYouCanGetFromChangingAnInteger {
    // time O(n)
    public int maxDiff(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int max = 0, min = 0, maxFrom = -1, minFrom = -1, minTo = -1;
        boolean maxSet = false, minSet = false;
        for(int i = 0; i < arr.length; i++){
            int cur = arr[i] - '0';

            if(maxSet){
                max = max * 10 + (cur == maxFrom ? 9 : cur);
            }else{
                max = max * 10 + 9;
                if(cur != 9){
                    maxSet = true;
                    maxFrom = cur;
                }
            }

            if(minSet){
                min = min * 10 + (cur == minFrom ? minTo : cur);
            }else{
                if(i == 0 && cur != 1){
                    min = min * 10 + 1;
                    minSet = true;
                    minFrom = cur;
                    minTo = 1;
                }else if(i > 0 && cur != 0 && cur != arr[0] - '0'){
                    min *= 10;
                    minSet = true;
                    minFrom = cur;
                    minTo = 0;
                }else{
                    min = min * 10 + cur;
                }
            }
        }
        return max - min;
    }
}
