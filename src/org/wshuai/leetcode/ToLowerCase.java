package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #709 https://leetcode.com/problems/to-lower-case/
 */
public class ToLowerCase {
    public String toLowerCase(String str) {
        if(str == null){
            return "";
        }
        int diff = 'a'-'A';
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                c = (char)(c + diff);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
