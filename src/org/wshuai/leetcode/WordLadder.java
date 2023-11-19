package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/2016.
 * #0127 https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {

	// time O(wl*n), space O(n) Bidirectional BFS
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(),
			dict = new HashSet<>(wordList), visited = new HashSet<>();
		if(!dict.contains(endWord)){
			return 0;
		}
		int steps = 0;
		beginSet.add(beginWord);
		endSet.add(endWord);
		while(beginSet.size() > 0 && endSet.size() > 0){
			if(beginSet.size() > endSet.size()){
				Set<String> swap = beginSet;
				beginSet = endSet;
				endSet = swap;
			}
			Set<String> temp = new HashSet<>();
			steps++;
			for(String cur : beginSet){
				char[] arr = cur.toCharArray();
				for(int i = 0; i < arr.length; i++){
					char old = arr[i];
					for(char c = 'a'; c <= 'z'; c++){
						arr[i] = c;
						String next = String.valueOf(arr);
						if(endSet.contains(next)){
							return steps + 1;
						}
						if(dict.contains(next) && !visited.contains(next)){
							visited.add(next);
							temp.add(next);
						}
					}
					arr[i] = old;
				}
			}
			beginSet = temp;
		}
		return 0;
	}

	// time O(wl*n), space O(n)
	public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
		Set<String> dict = new HashSet<>(wordList);
		if(!dict.contains(endWord)){
			return 0;
		}
		int steps = 0;
		LinkedList<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		visited.add(beginWord);
		queue.offerLast(beginWord);
		while(!queue.isEmpty()){
			steps++;
			int size = queue.size();
			while(size-- > 0){
				String cur = queue.pollFirst();
				char[] arr = cur.toCharArray();
				for(int i = 0; i < arr.length; i++){
					char old = arr[i];
					for(char c = 'a'; c <= 'z'; c++){
						arr[i] = c;
						String next = String.valueOf(arr);
						if(next.equals(endWord)){
							return steps + 1;
						}
						if(dict.contains(next) && !visited.contains(next)){
							visited.add(next);
							queue.offerLast(next);
						}
					}
					arr[i] = old;
				}
			}
		}
		return 0;
	}
}
