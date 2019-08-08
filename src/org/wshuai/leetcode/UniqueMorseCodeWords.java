package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/7/19.
 * #804 https://leetcode.com/problems/unique-morse-code-words/
 */
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        if(words == null || words.length == 0){
            return 0;
        }
        String[] MORSE = new String[]{".-","-...","-.-.","-..",".","..-.","--.",
                "....","..",".---","-.-",".-..","--","-.",
                "---",".--.","--.-",".-.","...","-","..-",
                "...-",".--","-..-","-.--","--.."};
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < words.length; i++){
            int sLen = words[i].length();
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while(j < sLen){
                int idx = words[i].charAt(j)-'a';
                sb.append(MORSE[idx]);
                j++;
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
