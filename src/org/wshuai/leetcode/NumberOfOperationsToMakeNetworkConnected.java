package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 01/12/2020.
 * #1319 https://leetcode.com/problems/number-of-operations-to-make-network-connected/
 */
public class NumberOfOperationsToMakeNetworkConnected {
	// time O(m*log(n)), space O(n)
	public int makeConnected(int n, int[][] connections) {
		int redunantConnections = 0;
		int[] root = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		for(int[] conn : connections){
			int a = conn[0];
			int b = conn[1];
			int ra = findRoot(a, root);
			int rb = findRoot(b, root);
			if(ra == rb){
				redunantConnections++;
			}
			root[rb] = ra;
		}
		Set<Integer> disconnectedComponents = new HashSet<>();
		for(int r : root){
			disconnectedComponents.add(findRoot(r, root));
		}
		if(disconnectedComponents.size() == 1){
			return 0;
		}
		return redunantConnections + 1 < disconnectedComponents.size() ? -1 : disconnectedComponents.size() - 1;
	}

	private int findRoot(int i, int[] root){
		if(i != root[i]){
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}
}
