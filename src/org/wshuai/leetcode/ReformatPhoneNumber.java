package org.wshuai.leetcode;

/**
 * Created by Wei on 12/21/2020.
 * #1694 https://leetcode.com/problems/reformat-phone-number/
 */
public class ReformatPhoneNumber {

    // time O(n), space O(n)
    public String reformatNumber(String number) {
        char[] arr = number.toCharArray();
        int j = 0, k = 0;
        for(int i = 0; i < arr.length; i++){
            if(Character.isDigit(arr[i])){
                arr[j++] = arr[i];
            }
        }
        StringBuilder res = new StringBuilder();
        for(; j > 4; j-= 3){
            res.append(arr[k++]).append(arr[k++]).append(arr[k++]).append("-");
        }
        if(j == 2){
            res.append(arr[k++]).append(arr[k++]);
        }else if(j == 3){
            res.append(arr[k++]).append(arr[k++]).append(arr[k++]);
        }else{
            res.append(arr[k++]).append(arr[k++]).append("-").append(arr[k++]).append(arr[k++]);
        }
        return res.toString();
    }
}
