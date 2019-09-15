package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 9/15/19.
 * #1087 https://leetcode.com/problems/brace-expansion/
 */
public class BraceExpansion {
    private List<String> res;

    public String[] expand(String S) {
        res = new ArrayList<>();
        char[] arr = S.toCharArray();
        formString(arr, 0, "");
        String[] ans = res.toArray(new String[res.size()]);
        Arrays.sort(ans);
        return ans;
    }

    private void formString(char[] arr, int index, String curr){
        if(index >= arr.length){
            res.add(curr);
            return;
        }
        if(arr[index] == '{'){
            int j = index;
            List<String> options = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            while(arr[j] != '}'){
                j++;
                if(arr[j] == ',' || arr[j] == '}'){
                    options.add(sb.toString());
                    sb = new StringBuilder();
                }else{
                    sb.append("" + arr[j]);
                }
            }
            for(String s: options){
                formString(arr, j+1, curr+s);
            }
        }else{
            curr += "" + arr[index];
            formString(arr, index+1, curr);
        }
    }
}
