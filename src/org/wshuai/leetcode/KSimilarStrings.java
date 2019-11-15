package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 11/15/19.
 * #854 https://leetcode.com/problems/k-similar-strings/
 */
public class KSimilarStrings {
	public int kSimilarity(String A, String B) {
		int res = 0;
		if(A.equals(B)){
			return res;
		}
		Set<String> visited = new HashSet<>();
		LinkedList<String> queue = new LinkedList<>();
		visited.add(A);
		queue.offerLast(A);
		while(!queue.isEmpty()){
			int size = queue.size();
			res++;
			while(size-- > 0){
				String s = queue.pollFirst();
				int i = 0;
				while(s.charAt(i) == B.charAt(i)){
					i++;
				}
				for(int j = i + 1; j < s.length(); j++){
					if(s.charAt(j) == B.charAt(j) || s.charAt(j) != B.charAt(i)){
						continue;
					}
					String temp = swap(s, i ,j);
					if(temp.equals(B)){
						return res;
					}
					if(visited.add(temp)){
						queue.offerLast(temp);
					}
				}
			}
		}
		return res;
	}

	private String swap(String s, int i, int j){
		char[] ca = s.toCharArray();
		char temp = ca[i];
		ca[i] = ca[j];
		ca[j] = temp;
		return new String(ca);
	}
}
