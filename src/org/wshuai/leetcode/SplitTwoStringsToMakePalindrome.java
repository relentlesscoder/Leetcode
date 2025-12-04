package org.wshuai.leetcode;

/**
 * Created by Wei on 10/13/2020.
 * #1616 https://leetcode.com/problems/split-two-strings-to-make-palindrome/
 */
public class SplitTwoStringsToMakePalindrome {

    // time O(n), space O(1)
    public boolean checkPalindromeFormation(String a, String b) {
        // Compare prefix(a) with suffix(b) and suffix(a) with prefix(b)
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int n = a.length(), i = 0, j = n - 1;
        while (i < n && a.charAt(i) == b.charAt(j)) {
            // There are more than half matches, so it is guaranteed to
            // form a palindrome.
            // e.g. [u,l,e,s,x,u]
            // and  [a,b,s,e,l,u]
            if (i++ >= j--) {
                return true;
            }
        }
        // If we find a valid prefix and suffix match for a[0, i] and
        // b[j, n - 1], then to form a valid palindrome substring one
        // of the below two conditions must be satisfied:
        //   1. a[i + 1, j - 1] is a palindrome
        //      e.g. [e,x,y,s,o,d,t,y,t,d,o,k,l,s]
        //      and  [i,s,u,i,e,u,s,i,u,e,s,y,x,e]
        //   2. b[i + 1, j - 1] is a palindrome
        //      e.g. [s,l,k,s,i,o,s,o,i,e,o,i,w,s]
        //      and  [e,x,y,s,o,d,t,y,t,d,o,k,l,s]
        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
