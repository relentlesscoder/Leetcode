package org.wshuai.leetcode;

/**
 * Created by Wei on 02/24/2020.
 * #1358 https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 */
public class ValidateBinaryTreeNodes {
	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		int[] root = new int[n], in = new int[n], out = new int[n];
		for(int i = 0; i < n; i++){
			root[i] = i;
		}
		for(int i = 0; i < n; i++){
			int r = findRoot(i, root), left = leftChild[i], right = rightChild[i];
			if(left != -1){
				int r1 = findRoot(left, root);
				if(r == r1){
					return false;
				}
				root[r1] = r;
				out[i]++;
				in[left]++;
			}
			if(right != -1){
				int r2 = findRoot(right, root);
				if(r == r2){
					return false;
				}
				root[r2] = r;
				out[i]++;
				in[right]++;
			}
			if(out[i] > 2){
				return false;
			}
		}
		int countRoot = 0;
		for(int i = 0; i < n; i++){
			if(in[i] > 1){
				return false;
			}
			if(in[i] == 0){
				countRoot++;
			}
		}
		return countRoot == 1;
	}

	private int findRoot(int i, int[] root){
		if(i != root[i]){
			root[i] = findRoot(root[i], root);
		}
		return root[i];
	}
}
