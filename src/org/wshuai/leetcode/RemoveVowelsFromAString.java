package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #1119 https://leetcode.com/problems/remove-vowels-from-a-string/
 */
public class RemoveVowelsFromAString {
    public String removeVowels(String S) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++){
            char c = S.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                continue;
            }
            sb.append("" + c);
        }
        return sb.toString();
    }
}
