package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/9/19.
 * #1078 https://leetcode.com/problems/occurrences-after-bigram/
 */
public class OccurrencesAfterBigram {
    public String[] findOcurrences(String text, String first, String second) {
        String[] arr = text.split(" ");
        if(arr.length < 3){
            return new String[0];
        }
        List<String> lst = new ArrayList<String>();
        for(int i = 2; i < arr.length; i++){
            if(arr[i-2].equals(first) && arr[i-1].equals(second)){
                lst.add(arr[i]);
            }
        }
        return lst.toArray(new String[lst.size()]);
    }
}
