package org.wshuai.leetcode;

/**
 * Created by Wei on 05/16/2021.
 * #1859 https://leetcode.com/problems/sorting-the-sentence/
 */
public class SortingTheSentence {

    public String sortSentence(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        String[] res = new String[strs.length];
        for(String str : strs){
            int index = str.charAt(str.length() - 1) - '1';
            res[index] = str.substring(0, str.length() - 1);
        }
        for(String str : res){
            sb.append(str).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
