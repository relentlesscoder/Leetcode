package org.wshuai.leetcode;

import java.util.LinkedList;

/**
 * Created by Wei on 09/15/2020.
 * #1585 https://leetcode.com/problems/check-if-string-is-transformable-with-substring-sort-operations/
 */
public class CheckIfStringIsTransformableWithSubstringSortOperations {

	// time O(n), space O(n)
	// https://www.youtube.com/watch?v=Pkd3FampKBk
	public boolean isTransformable(String s, String t) {
		int n = s.length();
		LinkedList<Integer>[] queue = new LinkedList[10];
		for(int i = 0; i < 10; i++){
			queue[i] = new LinkedList<>();
		}
		for(int i = 0; i < n; i++){
			int index = s.charAt(i) - '0';
			queue[index].offerLast(i);
		}
		for(int i = 0; i < n; i++){
			int index = t.charAt(i) - '0';
			if(queue[index].isEmpty()){
				return false;
			}
			for(int j = 0; j < index; j++){
				if(!queue[j].isEmpty()
					&& queue[j].peekFirst() < queue[index].peekFirst()){
					return false;
				}
			}
			queue[index].pollFirst();
		}
		return true;
	}
}
