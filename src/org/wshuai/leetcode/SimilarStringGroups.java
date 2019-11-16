package org.wshuai.leetcode;

/**
 * Created by Wei on 11/15/2019.
 * #839 https://leetcode.com/problems/similar-string-groups/
 */
public class SimilarStringGroups {
	public int numSimilarGroups(String[] A) {
		int N = A.length;
		int[] root = new int[N];
		//int[] rank = new int[N];
		for(int i = 0; i < N; i++){
			root[i] = i;
		}
		int res = N;
		for(int i = 0; i < N; i++){
			for(int j = i + 1; j < N; j++){
				if(isSimilar(A[i], A[j])){
					int ri = findRoot(i, root);
					int rj = findRoot(j, root);
					if(ri == rj){
						continue;
					}
					res--;
                    /*
                    if(rank[ri] > rank[rj]){
                        root[rj] = ri;
                        rank[ri] += rank[rj];
                    }else{
                        root[ri] = rj;
                        rank[rj] += rank[ri];
                    }
                    */
					root[ri] = rj;
				}
			}
		}
		return res;
	}

	private int findRoot(int i, int[] root){
		if(i != root[i]){
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}

	private boolean isSimilar(String a, String b){
		int cnt = 0;
		int i = 0;
		while(i < a.length()){
			if(a.charAt(i) != b.charAt(i)){
				cnt++;
				if(cnt > 2){
					return false;
				}
			}
			i++;
		}
		return true;
	}
}
