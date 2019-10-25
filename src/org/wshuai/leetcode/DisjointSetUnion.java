package org.wshuai.leetcode;

public class DisjointSetUnion {

	private int[] root;
	private int[] rank;

	public DisjointSetUnion(int n){
		root = new int[n];
		rank = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
			rank[i] = 1;
		}
	}

	public int find(int v){
		if(root[v] != v){
			root[v] = find(root[v]);
		}
		return root[v];
	}

	public boolean union(int x, int y){
		int rootX = find(x);
		int rootY = find(y);
		if(rootX == rootY){
			return false;
		}

		if(rank[rootX] < rank[rootY]){
			root[rootX] = rootY;
			rank[rootY] += rank[rootX];
		}else{
			root[rootY] = rootX;
			rank[rootX] += rank[rootY];
		}
		return true;
	}

	boolean isConnected(int x, int y){
		return find(x) == find(y);
	}
}
