package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 8/5/19.
 * #771 https://leetcode.com/problems/jewels-and-stones/
 */
public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Character> types = new HashSet<Character>();
        for(int i = 0; i < J.length(); i++){
            types.add(J.charAt(i));
        }
        for(int i = 0; i < S.length(); i++){
            if(types.contains(S.charAt(i))){
                res++;
            }
        }
        return res;
    }
}
