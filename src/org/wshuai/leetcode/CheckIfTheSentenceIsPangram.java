package org.wshuai.leetcode;

/**
 * Created by Wei on 05/19/2021.
 * #1832 https://leetcode.com/problems/check-if-the-sentence-is-pangram/
 */
public class CheckIfTheSentenceIsPangram {

    // time O(n)
    public boolean checkIfPangram(String sentence) {
        int count = 26;
        boolean[] seen = new boolean[26];
        for(char c : sentence.toCharArray()){
            int index = c - 'a';
            if(seen[index]){
                continue;
            }
            seen[index] = true;
            count--;
        }
        return count == 0;
    }
}
