package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #859 https://leetcode.com/problems/buddy-strings/
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        int len1 = A.length();
        int len2 = B.length();
        if(len1 != len2 || len1 < 2){
            return false;
        }
        // handles the case that two strings are equal
        if(A.equals(B)){
            int[] count = new int[26];
            for (int i = 0; i < A.length(); i++){
                count[A.charAt(i) - 'a']++;
            }
            // only if there is any repetitive character
            for (int c: count){
                if (c > 1) return true;
            }
            return false;
        }else{
            int count = 0;
            int[] diff = new int[2];
            for(int i = 0; i < len1; i++){
                if(A.charAt(i) == B.charAt(i)){
                    continue;
                }
                if(count >= 2){
                    return false;
                }
                diff[count] = i;
                count++;
            }
            return (count == 2)
                    && (A.charAt(diff[0]) == B.charAt(diff[1]))
                    && (A.charAt(diff[1]) == B.charAt(diff[0]));
        }
    }
}
