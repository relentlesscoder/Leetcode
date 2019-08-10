package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Wei on 8/9/19.
 * #819 https://leetcode.com/problems/most-common-word/
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        Set<String> set = new HashSet<String>();
        for(String str: banned){
            set.add(str);
        }
        String res = "";
        int max = 0;
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(char c: paragraph.toCharArray()){
            if(Character.isLetter(c)){
                sb.append("" + Character.toLowerCase(c));
            }else{
                if(sb.length() > 0){
                    String word = sb.toString();
                    int count = map.getOrDefault(word, 0) + 1;
                    map.put(word, count);
                    if(!set.contains(word) && count > max){
                        max = count;
                        res = word;
                    }
                    sb = new StringBuilder();
                }
            }
        }
        return res;
    }
}
