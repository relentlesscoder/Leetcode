package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 8/25/19.
 * #1100 https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters {
    public int numKLenSubstrNoRepeats(String S, int K) {
        int count = 0;
        LinkedList<Character> linkedList = new LinkedList<>();
        char[] arr = S.toCharArray();
        int right = 0;
        while(right < arr.length){
            if(!linkedList.contains(arr[right])){
                if(linkedList.size() == K){
                    linkedList.pop();
                }
            }else{
                while(linkedList.peek() != arr[right]){
                    linkedList.pop();
                }
                linkedList.pop();
            }
            linkedList.offer(arr[right++]);
            if(linkedList.size() == K){
                count++;
            }
        }
        return count;
    }
}
