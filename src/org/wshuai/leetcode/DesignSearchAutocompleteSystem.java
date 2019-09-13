package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/9/2019.
 * #642 https://leetcode.com/problems/design-search-autocomplete-system/
 */
public class DesignSearchAutocompleteSystem {
    private AutoCompleteTrieNode root;

    private String curr;

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new AutoCompleteTrieNode();
        curr = "";

        for(int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();

        if(c == '#'){
            add(curr, 1);
            curr = "";
            return res;
        }

        curr += c;

        AutoCompleteTrieNode node = root;
        for(int i = 0; i < curr.length(); i++){
            char key = curr.charAt(i);
            if(node.containsKey(key)){
                node = node.get(key);
            }else{
                return res;
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> queue =
                new PriorityQueue<>((a, b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey().compareTo(b.getKey()));
        for(Map.Entry<String, Integer> entry: node.getWordCount()){
            queue.offer(entry);
        }

        int k = 3;
        while(k > 0 && !queue.isEmpty()){
            res.add(queue.poll().getKey());
            k--;
        }

        return res;
    }

    private void add(String word, int count){
        AutoCompleteTrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            char key = word.charAt(i);
            if(!node.containsKey(key)){
                node.put(key, new AutoCompleteTrieNode());
            }
            node = node.get(key);
            node.addWordCount(word, count);
        }
    }
}

class AutoCompleteTrieNode{

    private final int R = 27;

    private AutoCompleteTrieNode[] links;

    private Map<String, Integer> counts;

    public AutoCompleteTrieNode(){
        links = new AutoCompleteTrieNode[R];

        counts = new HashMap<>();
    }

    public boolean containsKey(char key){
        AutoCompleteTrieNode node = (key == ' ' ? links[26] : links[key-'a']);
        return  node != null;
    }

    public AutoCompleteTrieNode get(char key){
        AutoCompleteTrieNode node = (key == ' ' ? links[26] : links[key-'a']);
        return node;
    }

    public void put(char key, AutoCompleteTrieNode node){
        int index = (key == ' ' ? 26 : (int)(key-'a'));
        links[index] = node;
    }

    public void addWordCount(String word, int count){
        counts.put(word, counts.getOrDefault(word, 0) + count);
    }

    public Set<Map.Entry<String, Integer>> getWordCount(){
        return counts.entrySet();
    }
}
