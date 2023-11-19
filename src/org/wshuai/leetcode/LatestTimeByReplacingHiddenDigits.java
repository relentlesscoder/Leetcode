package org.wshuai.leetcode;

/**
 * Created by Wei on 02/02/2021.
 * #1736 https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/
 */
public class LatestTimeByReplacingHiddenDigits {

    public String maximumTime(String time) {
        char[] arr = time.toCharArray();
        if(arr[0] == '?' && arr[1] == '?'){
            arr[0] = '2';
            arr[1] = '3';
        }else if(arr[0] == '?'){
            arr[0] = arr[1] >= '4' ? '1' : '2';
        }else if(arr[1] == '?'){
            arr[1] = arr[0] == '2' ? '3' : '9';
        }
        arr[3] = arr[3] == '?' ? '5' : arr[3];
        arr[4] = arr[4] == '?' ? '9' : arr[4];
        return String.valueOf(arr);
    }
}
