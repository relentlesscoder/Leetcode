package org.wshuai.leetcode;

/**
 * Created by Wei on 11/20/2023.
 * #1967 https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/
 */
public class NumberOfStringsThatAppearAsSubstringsInWord {

    // time O(m * n), space O(m * n)
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;
        char[] w = word.toCharArray();
        for (String p : patterns) {
            if (kmp(w, p.toCharArray()) != -1) {
                res++;
            }
        }
        return res;
    }

    private int kmp(char[] text, char[] pattern){
        int[] lsp = constructLsp(pattern);
        int j = 0;
        for(int i = 0; i < text.length; i++){
            while(j > 0 && text[i] != pattern[j]){
                j = lsp[j - 1];
            }
            if(text[i] == pattern[j]){
                j++;
                if(j == pattern.length){
                    return i - j + 1;
                }
            }
        }
        return -1;
    }

    private int[] constructLsp(char[] pattern){
        int[] lsp = new int[pattern.length];
        lsp[0] = 0;
        int j = 0;
        for(int i = 1; i < pattern.length; i++){
            // consider the case when pattern is AAACAAAA
            // when i = 7, the LSP is
            // 0,1,2,0,1,2,3
            // since S[i] != S[LSP[i - 1]] (A != C), we can extend
            // the previous matching prefix suffix
            // LSP[6] = 3 -> S[0-2] == S[4-6] -> S[1-2] == S[5,6]
            // LSP[3 - 1] = 2 -> S[0-1] == S[1-2]
            // -> S[5,6] == S[0-1]
            // if S[2] == S[7], we can extend this matching prefix
            // postfix to 3
            while(j > 0 && pattern[i] != pattern[j]){
                j = lsp[j - 1];
            }
            if(pattern[i] == pattern[j]){
                j++;
            }
            lsp[i] = j;
        }
        return lsp;
    }
}
