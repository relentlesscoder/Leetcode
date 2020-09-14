package org.wshuai.leetcode;

/**
 * Created by Wei on 09/14/2020.
 * #1540 https://leetcode.com/problems/can-convert-string-in-k-moves/
 */
public class CanConvertStringInKMoves {

    // time O(n)
    public boolean canConvertString(String s, String t, int k) {
        int m = s.length(), n = t.length();
        if(m != n){
            return false;
        }
        int[] mov = new int[26];
        for(int i = 0; i < n; i++){
            char sc = s.charAt(i), tc = t.charAt(i);
            if(sc == tc){
                continue;
            }
            int shift = tc - sc;
            if(shift < 0){
                shift += 26;
            }
            // s = "aab", t = "bbb"
            // at index 0, we make a -> b at 1st shift
            // at index 1, we make a -> b at 27st shift
            // ...
            if(mov[shift] == 0){
                mov[shift] = shift;
            }else{
                mov[shift] += 26;
            }
            if(k < mov[shift]){
                return false;
            }
        }
        return true;
    }
}
