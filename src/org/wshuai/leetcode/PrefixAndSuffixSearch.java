package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/19.
 * #745 https://leetcode.com/problems/prefix-and-suffix-search/
 */
public class PrefixAndSuffixSearch {
	private PrefixAndSuffixTrieNode root;

	public PrefixAndSuffixSearch(String[] words) {
		root = new PrefixAndSuffixTrieNode();
		for(int i = 0; i < words.length; i++){
			String word = words[i];
			insert("{" + word, i);
			for(int j = word.length() - 1; j >= 0; j--){
				insert(word.substring(j) + "{" + word, i);
			}
		}
	}

	public int f(String prefix, String suffix) {
		PrefixAndSuffixTrieNode node = search(suffix + "{" + prefix);
		return node == null ? -1 : node.getWeight();
	}

	private void insert(String word, int weight){
		PrefixAndSuffixTrieNode curr = root;
		root.setWeight(weight);
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!curr.containsKey(c)){
				curr.put(c, new PrefixAndSuffixTrieNode());
			}
			curr = curr.get(c);
			curr.setWeight(weight);
		}
	}

	private PrefixAndSuffixTrieNode search(String word){
		PrefixAndSuffixTrieNode curr = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(!curr.containsKey(c)){
				return null;
			}
			curr = curr.get(c);
		}
		return curr;
	}
}

class PrefixAndSuffixTrieNode{

	private final int R = 27;

	private PrefixAndSuffixTrieNode[] links;

	private int weight;

	public PrefixAndSuffixTrieNode(){
		links = new PrefixAndSuffixTrieNode[R];
		weight = 0;
	}

	public boolean containsKey(char key){
		return links[key - 'a'] != null;
	}

	public PrefixAndSuffixTrieNode get(char key){
		return links[key - 'a'];
	}

	public void put(char key, PrefixAndSuffixTrieNode node){
		links[key - 'a'] = node;
	}

	public void setWeight(int w){
		weight = w;
	}

	public int getWeight(){
		return weight;
	}
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
