package org.wshuai.leetcode;

/**
 * Created by Wei on 04/06/2025.
 * #3043 https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
 */
public class FindTheLengthOfTheLongestCommonPrefix {

    // time O(m + n), space O(m + n)
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int res = 0;
        TrieNode root = new TrieNode();
        for (int num : arr1) {
            insert((Integer.toString(num)).toCharArray(), root);
        }
        for (int num : arr2) {
            res = Math.max(res, countCommonPrefix((Integer.toString(num)).toCharArray(), root));
        }
        return res;
    }

    private void insert(char[] num, TrieNode root) {
        TrieNode curr = root;
        for (char c : num) {
            if (!curr.containsKey(c)) {
                curr.set(c, new TrieNode());
            }
            curr = curr.get(c);
        }
        curr.setEnd();
    }

    private int countCommonPrefix(char[] num, TrieNode root) {
        TrieNode curr = root;
        int count = 0;
        for (char c : num) {
            if (!curr.containsKey(c)) {
                break;
            }
            count++;
            curr = curr.get(c);
        }
        return count;
    }

    private class TrieNode {

        private TrieNode[] nodes;
        private boolean isEnd;

        TrieNode() {
            nodes = new TrieNode[10];
            isEnd = false;
        }

        private boolean containsKey(char c) {
            return nodes[c - '0'] != null;
        }

        private TrieNode get(char c) {
            return nodes[c - '0'];
        }

        private void set(char c, TrieNode node) {
            nodes[c - '0'] = node;
        }

        private boolean isEnd() {
            return this.isEnd;
        }

        private void setEnd() {
            this.isEnd = true;
        }
    }
}
