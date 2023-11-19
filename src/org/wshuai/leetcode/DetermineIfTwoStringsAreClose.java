package org.wshuai.leetcode;

/**
 * Created by Wei on 01/14/2021.
 * #1657 https://leetcode.com/problems/determine-if-two-strings-are-close/
 */
public class DetermineIfTwoStringsAreClose {

    // time O(n)
    public boolean closeStrings(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m != n){
            return false;
        }
        int[] count1 = new int[26], count2 = new int[26];
        for(char c : word1.toCharArray()){
            count1[c - 'a']++;
        }
        for(char c : word2.toCharArray()){
            if(count1[c - 'a'] == 0){
                return false;
            }
            count2[c - 'a']++;
        }
        for(int i = 0; i < 26; i++){
            if(count1[i] == 0){
                continue;
            }
            boolean found = false;
            for(int j = 0; j < 26; j++){
                if(count2[j] == count1[i]){
                    count2[j] = 0;
                    found = true;
                    break;
                }
            }
            if(!found){
                return false;
            }
        }
        return true;
    }
}
