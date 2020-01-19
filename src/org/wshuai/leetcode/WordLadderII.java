package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/04/2016.
 * #0126 https://leetcode.com/problems/word-ladder-ii/
 */
public class WordLadderII {

	// time O(wl*n), space O(n), bidirectional BFS
	// http://zxi.mytechroad.com/blog/searching/leetcode-126-word-ladder-ii/
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		Set<String> wordSet = new HashSet<>(wordList), beginSet = new HashSet<>(), endSet = new HashSet<>();
		if(!wordSet.contains(endWord)){
			return res;
		}
		Map<String, Set<String>> children = new HashMap<>();
		boolean found = false;
		boolean reversed = false;
		beginSet.add(beginWord);
		endSet.add(endWord);
		while(!beginSet.isEmpty() && !endSet.isEmpty() && !found){
			if(beginSet.size() > endSet.size()){
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
				reversed = !reversed;
			}
			for(String s : beginSet){
				wordSet.remove(s);
			}
			for(String s : endSet){
				wordSet.remove(s);
			}
			Set<String> queue = new HashSet<>();
			for(String cur : beginSet){
				char[] arr = cur.toCharArray();
				for(int i = 0; i < arr.length; i++){
					char old = arr[i];
					for(char c = 'a'; c <= 'z'; c++){
						if(c == old){
							continue;
						}
						arr[i] = c;
						String next = new String(arr);
						String parent = cur;
						String child = next;
						if(reversed){
							String temp = parent;
							parent = child;
							child = temp;
						}
						if(endSet.contains(next)){
							found = true;
							children.putIfAbsent(parent, new HashSet<>());
							children.get(parent).add(child);
						}else if(wordSet.contains(next) && !found){
							queue.add(next);
							children.putIfAbsent(parent, new HashSet<>());
							children.get(parent).add(child);
						}
					}
					arr[i] = old;
				}
			}
			beginSet = queue;
			queue = new HashSet<>();
		}
		if(found){
			List<String> cur = new ArrayList<>();
			cur.add(beginWord);
			dfs(beginWord, children, endWord, cur, res);
		}
		return res;
	}

	private void dfs(String s, Map<String, Set<String>> children, String t, List<String> cur, List<List<String>> res){
		if(s.equals(t)){
			res.add(new ArrayList<String>(cur));
			return;
		}
		if(!children.containsKey(s)){
			return;
		}
		for(String nxt : children.get(s)){
			cur.add(nxt);
			dfs(nxt, children, t, cur, res);
			cur.remove(cur.size() - 1);
		}
	}

	public List<List<String>> findLaddersBFS(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<>();
		Set<String> wordSet = new HashSet<>(wordList);
		if(!wordSet.contains(endWord)){
			return res;
		}
		wordSet.remove(endWord);
		Map<String, Set<String>> parents = new HashMap<>();
		Map<String, Integer> distance = new HashMap<>();
		LinkedList<String> queue = new LinkedList<>();

		boolean found = false;
		int dist = 0;
		queue.offerLast(beginWord);
		distance.put(beginWord, 1);
		while(!queue.isEmpty() && !found){
			dist++;
			int size = queue.size();
			while(size-- > 0){
				String cur = queue.pollFirst();
				char[] arr = cur.toCharArray();
				for(int i = 0; i < arr.length; i++){
					char old = arr[i];
					for(char c = 'a'; c <= 'z'; c++){
						if(c == old){
							continue;
						}
						arr[i] = c;
						String next = new String(arr);
						if(next.equals(endWord)){
							found = true;
							parents.putIfAbsent(next, new HashSet<>());
							parents.get(next).add(cur);
						}else if(dist + 1 == distance.getOrDefault(next, 0)){
							parents.putIfAbsent(next, new HashSet<>());
							parents.get(next).add(cur);
						}
						if(!wordSet.contains(next)){
							continue;
						}
						wordSet.remove(next);
						queue.offerLast(next);
						parents.putIfAbsent(next, new HashSet<>());
						parents.get(next).add(cur);
						distance.put(next, distance.get(cur) + 1);
					}
					arr[i] = old;
				}
			}
		}
		if(found){
			LinkedList<String> cur = new LinkedList<>();
			cur.offerFirst(endWord);
			dfs(endWord, parents, beginWord, cur, res);
		}
		return res;
	}

	private void dfs(String s, Map<String, Set<String>> parents, String t, LinkedList<String> cur, List<List<String>> res){
		if(s.equals(t)){
			res.add(new ArrayList<>(cur));
			return;
		}
		for(String nxt : parents.get(s)){
			cur.offerFirst(nxt);
			dfs(nxt, parents, t, cur, res);
			cur.pollFirst();
		}
	}
}
