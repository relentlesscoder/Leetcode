package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #3042 https://leetcode.com/problems/count-prefix-and-suffix-pairs-i/
 */
public class CountPrefixAndSuffixPairsI {

    public int countPrefixSuffixPairs(String[] words) {
        int res = 0;
        TrieNode prefixTrie = new TrieNode(), postfixTrie = new TrieNode();
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i], reverseWord = new StringBuilder(word).reverse().toString();
            int[] prefixIndexArr = countPrefix(word, prefixTrie);
            int[] postfixIndexArr = countPrefix(reverseWord, postfixTrie);
            if (prefixIndexArr.length > 0 && postfixIndexArr.length > 0) {
                for (int j = 0; j < 50; j++) {
                    if (prefixIndexArr[j] == 1 && postfixIndexArr[j] == 1) {
                        res++;
                    }
                }
            }
            buildPrefixTrie(word, i, prefixTrie);
            buildPrefixTrie(reverseWord, i, postfixTrie);
        }
        return res;
    }

    private void buildPrefixTrie(String word, int index, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                curr.put(c, new TrieNode());
            }
            curr = curr.get(c);
            curr.setIndex(index);
        }
    }

    private int[] countPrefix(String word, TrieNode root) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (!curr.containsKey(c)) {
                return new int[0];
            }
            curr = curr.get(c);
        }
        return curr.getIndexArray();
    }

    private static class TrieNode {
        TrieNode[] nodes;
        int[] indexArray;

        TrieNode() {
            nodes = new TrieNode[26];
            indexArray = new int[50];
        }

        private void put(char c, TrieNode node) {
            nodes[c - 'a'] = node;
        }

        private boolean containsKey(char c) {
            return nodes[c - 'a'] != null;
        }

        private TrieNode get(char c) {
            return nodes[c - 'a'];
        }

        private void setIndex(int index) {
            indexArray[index] = 1;
        }

        private int[] getIndexArray() {
            return indexArray;
        }
    }
}
