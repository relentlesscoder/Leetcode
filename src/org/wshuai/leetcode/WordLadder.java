package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/17/2016.
 * #0127 https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {
	// time O(wl*n) bidirectional BFS
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(),
				wordSet = new HashSet<>(wordList), visited = new HashSet<>();
		if(!wordSet.contains(endWord)){
			return 0;
		}
		int len = 1;
		beginSet.add(beginWord);
		endSet.add(endWord);
		while(!beginSet.isEmpty() && !endSet.isEmpty()){
			if(beginSet.size() > endSet.size()){
				Set<String> temp = beginSet;
				beginSet = endSet;
				endSet = temp;
			}

			Set<String> cur = new HashSet<String>();
			for(String word : beginSet){
				char[] arr = word.toCharArray();
				for(int i = 0; i < arr.length; i++){
					char old = arr[i];
					for(char c = 'a'; c <= 'z'; c++){
						if(c == old){
							continue;
						}
						arr[i] = c;
						String next = new String(arr);
						if(endSet.contains(next)){
							return len + 1;
						}
						if(!visited.contains(next) && wordSet.contains(next)){
							cur.add(next);
							visited.add(next);
						}
					}
					arr[i] = old;
				}
			}
			beginSet = cur;
			len++;
		}
		return 0;
	}

	// time O(wl*n)
	public int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
		LinkedList<String> queue = new LinkedList<>();
		Set<String> set = new HashSet<>(wordList);
		int step = 0;
		queue.offerLast(beginWord);
		while(!queue.isEmpty()){
			int size = queue.size();
			step++;
			while(size-- > 0){
				String str = queue.poll();
				if(str.equals(endWord)){
					return step;
				}
				char[] cur = str.toCharArray();
				for(int i = 0; i < cur.length; i++){
					char c = cur[i];
					for(int j = 0; j < 26; j++){
						if(j == c - 'a'){
							continue;
						}
						cur[i] = (char)('a' + j);
						String next = new String(cur);
						if(!set.contains(next)){
							continue;
						}
						set.remove(next);
						queue.offerLast(next);
					}
					cur[i] = c;
				}
			}
		}
		return 0;
	}
}
