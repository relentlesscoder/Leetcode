package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/21/19.
 * #830 https://leetcode.com/problems/positions-of-large-groups/
 */
public class PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        // add a postfix to avoid missing the last character group
        S += ".";
        char[] arr = S.toCharArray();
        char curr = '-';
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == curr){
                count++;
            }else{
                if(curr != '-' && count >= 3){
                    List<Integer> lst = new ArrayList<>();
                    lst.add(i - count);
                    lst.add(i - 1);
                    res.add(lst);
                }
                count = 1;
                curr = arr[i];
            }
        }
        return res;
    }
}
