package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 12/2/19.
 * #691 https://leetcode.com/problems/stickers-to-spell-word/
 */
public class StickersToSpellWord {
	public int minStickers(String[] stickers, String target) {
		int N = stickers.length;
		int T = target.length();
		int[][] letters = new int[N][26];
		int[] targetLetters = new int[26];
		for(int i = 0; i < N; i++){
			for(char c : stickers[i].toCharArray()){
				letters[i][c - 'a']++;
			}
		}
		for(char c: target.toCharArray()){
			targetLetters[c - 'a']++;
		}

		int level = 0;
		LinkedList<int[]> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offerLast(targetLetters);
		while(!queue.isEmpty()){
			int size = queue.size();
			while(size-- > 0){
				int[] cur = queue.pollFirst();
				for(int i = 0; i < N; i++){
					int[] next = cur.clone();
					for(int j = 0; j < 26; j++){
						int left = next[j] - letters[i][j];
						next[j] = left > 0 ? left : 0;
					}
					String key = toKey(next);
					if(key.length() == 0){
						return level + 1;
					}
					if(visited.add(key)){
						queue.offer(next);
					}
				}
			}
			level++;
		}

		return -1;
	}

	private String toKey(int[] arr){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 26; i++){
			for(int j = 0; j < arr[i]; j++){
				sb.append((char)('a' + i));
			}
		}
		return sb.toString();
	}
}
