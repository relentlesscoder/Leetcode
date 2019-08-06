package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/5/19.
 * #336 https://leetcode.com/problems/palindrome-pairs/
 */
public class PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        if(words == null || words.length < 2){
            return null;
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < words.length; i++){
            String rev = new StringBuilder(words[i]).reverse().toString();
            map.put(rev, i);
        }
        for(int i = 0; i < words.length; i++){
            int len = words[i].length();
            for(int j = 0; j <= len; j++){
                String left = words[i].substring(0, j);
                String right = j == len ? "" : words[i].substring(j);
                if(isPalindrome(left) && map.containsKey(right) && map.get(right) != i){
                    List<Integer> lst2 = new ArrayList<Integer>();
                    lst2.add(map.get(right));
                    lst2.add(i);
                    res.add(lst2);
                }
                if(isPalindrome(right) && map.containsKey(left) && map.get(left) != i
                        //avoid duplicates
                        && right.length() != 0){
                    List<Integer> lst2 = new ArrayList<Integer>();
                    lst2.add(i);
                    lst2.add(map.get(left));
                    res.add(lst2);
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str){
        if(str == null || str.length() <= 1){
            return true;
        }
        int left = 0;
        int right = str.length()-1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
