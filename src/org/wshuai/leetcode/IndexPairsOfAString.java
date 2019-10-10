package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 8/21/19.
 * #1065 https://leetcode.com/problems/index-pairs-of-a-string/
 */
public class IndexPairsOfAString {
	public int[][] indexPairs(String text, String[] words) {
		ImplementTrie trie = new ImplementTrie();
		for (String w : words) {
			trie.insert(w);
		}
		List<int[]> lst = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			for (int j = i + 1; j <= text.length(); j++) {
				String s = text.substring(i, j);
				if (trie.search(s)) {
					int[] arr = new int[2];
					arr[0] = i;
					arr[1] = j - 1;
					lst.add(arr);
				}
			}
		}
		return lst.toArray(new int[lst.size()][2]);
	}
}
