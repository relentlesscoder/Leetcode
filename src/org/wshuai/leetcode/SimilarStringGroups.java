package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2019.
 * #0839 https://leetcode.com/problems/similar-string-groups/
 */
public class SimilarStringGroups {

	// time O(n*log(n)), space O(n)
	public int numSimilarGroups(String[] A) {
		int n = A.length, res = n;
		int[] root = new int[n], rank = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
			rank[i] = 1;
		}
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				if(!similar(A[i], A[j])){
					continue;
				}
				int r1 = findRoot(i, root);
				int r2 = findRoot(j, root);
				if(r1 == r2){
					continue;
				}
				if(rank[r1] > rank[r2]){
					root[r2] = r1;
					rank[r1] += rank[r2];
				}else{
					root[r1] = r2;
					rank[r2] += rank[r1];
				}
				res--;
			}
		}
		return res;
	}

	private int findRoot(int r, int[] root){
		if(r != root[r]){
			root[r] = findRoot(root[r], root);
		}
		return root[r];
	}

	private boolean similar(String a, String b){
		int diff = 0;
		for(int i = 0; i < a.length(); i++){
			if(a.charAt(i) != b.charAt(i)){
				if(++diff > 2){
					return false;
				}
			}
		}
		return true;
	}
}
