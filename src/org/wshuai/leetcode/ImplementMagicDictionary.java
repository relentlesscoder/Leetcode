package org.wshuai.leetcode;

/**
 * Created by Wei on 09/18/2019.
 * #0676 https://leetcode.com/problems/implement-magic-dictionary/
 */
public class ImplementMagicDictionary {

	private TrieNode root;

	/** Initialize your data structure here. */
	public ImplementMagicDictionary() {
		root = new TrieNode();
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		for(String word : dict){
			insert(word);
		}
	}

	/** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
	public boolean search(String word) {
		return search(word, root, 0, 0);
	}

	private void insert(String word){
		TrieNode node = root;
		for(char c : word.toCharArray()){
			if(!node.containsKey(c)){
				node.put(c, new TrieNode());
			}
			node = node.get(c);
		}
		node.setEnd();
	}

	private boolean search(String word, TrieNode cur, int i, int missed){
		if(i == word.length() && cur.isEnd() && missed == 1){
			return true;
		}
		if(i == word.length() || missed > 1){
			return false;
		}
		char c = word.charAt(i);
		for(char x = 'a'; x <= 'z'; x++){
			if(cur.containsKey(x) && search(word, cur.get(x), i + 1, missed + (x == c ? 0 : 1))){
				return true;
			}
		}
		return false;
	}

	/* implementation using hash map

	private Map<String, List<int[]>> map;

	*//** Initialize your data structure here. *//*
	public ImplementMagicDictionary() {
		map = new HashMap<>();
	}

	*//** Build a dictionary through a list of words *//*
	public void buildDict(String[] dict) {
		for(String word : dict){
			for(int i = 0; i < word.length(); i++){
				String key = word.substring(0, i) + word.substring(i + 1);
				map.putIfAbsent(key, new ArrayList<>());
				map.get(key).add(new int[]{i, word.charAt(i)});
			}
		}
	}

	*//** Returns if there is any word in the trie that equals to the given word after modifying exactly one character *//*
	public boolean search(String word) {
		for(int i = 0; i < word.length(); i++){
			String key = word.substring(0, i) + word.substring(i + 1);
			if(!map.containsKey(key)){
				continue;
			}
			for(int[] arr : map.get(key)){
				if(arr[0] == i && arr[1] != word.charAt(i)){
					return true;
				}
			}
		}
		return false;
	}*/
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */