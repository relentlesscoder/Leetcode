package org.wshuai.leetcode;

/**
 * Created by Wei on 9/5/19.
 * #791 https://leetcode.com/problems/custom-sort-string/
 */
public class CustomSortString {

    // count and write
    public String customSortString(String S, String T) {
        int[] map = new int[26];
        for(char c: T.toCharArray()){
            map[c-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(char c: S.toCharArray()){
            int count = map[c-'a'];
            while(count > 0){
                sb.append("" + c);
                count--;
            }
            map[c-'a'] = 0;
        }
        for(int i = 0; i < 26; i++){
            if(map[i] > 0){
                int count = map[i];
                while(count > 0){
                    char c = (char)(i + 'a');
                    sb.append("" + c);
                    count--;
                }
            }
        }

        return sb.toString();
    }
}
