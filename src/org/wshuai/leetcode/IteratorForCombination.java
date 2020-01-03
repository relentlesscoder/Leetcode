package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/15/2019.
 * #1286 https://leetcode.com/problems/iterator-for-combination/
 */
public class IteratorForCombination {
	private List<String> list;
	private int index;

	public IteratorForCombination(String characters, int combinationLength) {
		index = 0;
		list = new ArrayList<>();
		char[] chars = characters.toCharArray();
		for(int i = 0; i < chars.length; i++){
			dfs(i, chars, "" + chars[i], combinationLength);
		}
	}

	private void dfs(int start, char[] arr, String cur, int len){
		if(cur.length() == len){
			list.add(cur);
			return;
		}
		if(start >= arr.length){
			return;
		}
		for(int j = start + 1; j < arr.length; j++){
			dfs(j, arr, cur + arr[j], len);
		}
	}

	public String next() {
		return list.get(index++);
	}

	public boolean hasNext() {
		return index < list.size();
	}
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
