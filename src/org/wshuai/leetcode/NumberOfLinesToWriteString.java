package org.wshuai.leetcode;

/**
 * Created by Wei on 8/22/19.
 * #806 https://leetcode.com/problems/number-of-lines-to-write-string/
 */
public class NumberOfLinesToWriteString {
    public int[] numberOfLines(int[] widths, String S) {
        char[] arr = S.toCharArray();
        int sum = 0;
        int lines = 0;
        for(char c: arr){
            int width = widths[c-'a'];
            if(sum + width > 100){
                lines++;
                sum = width;
            }else{
                sum += width;
            }
        }
        int[] res = new int[2];
        if(sum == 0){
            res[0] = lines;
            res[1] = 0;
        }else{
            res[0] = lines + 1;
            res[1] = sum;
        }
        return res;
    }
}
