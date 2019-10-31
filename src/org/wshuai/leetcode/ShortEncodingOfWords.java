package org.wshuai.leetcode;

/**
 * Created by Wei on 10/31/2019.
 * #820 https://leetcode.com/problems/short-encoding-of-words/
 */
public class ShortEncodingOfWords {

	// slow
	public int minimumLengthEncodingUnionFind(String[] words) {
		int N = words.length;
		int[] root = new int[N];
		for(int i = 0; i < N; i++){
			root[i] = i;
		}
		for(int i = 0; i < N; i++){
			for(int j = i + 1; j < N; j++){
				int r1 = find(i, root);
				int r2 = find(j, root);
				String s1 = words[r1];
				String s2 = words[r2];
				if(s1.equals(s2)){
					root[r2] = r1;
					continue;
				}
				if(s1.length() > s2.length() && s1.endsWith(s2)){
					root[r2] = r1;
				}
				if(s2.length() > s1.length() && s2.endsWith(s1)){
					root[r1] = r2;
				}
			}
		}
		int res = 0;
		int cnt = 0;
		for(int i = 0; i < N; i++){
			if(root[i] == i){
				cnt++;
				res += words[i].length();
			}
		}
		res += cnt;
		return res;
	}

	private int find(int i, int[] root){
		if(root[i] != i){
			root[i] = find(root[i], root);
		}
		return root[i];
	}
}
