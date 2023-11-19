package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #1901 https://leetcode.com/problems/find-a-peak-element-ii/
 */
public class FindAPeakElementII {

	// time O(m * log(n)), space O(1)
	public int[] findPeakGrid(int[][] mat) {
		// same idea as #0162 https://leetcode.com/problems/find-peak-element/
		// proof - https://leetcode.com/problems/find-a-peak-element-ii/solutions/1330084/proof-of-why-the-binary-search-approach-is-correct/
		int m = mat.length, n = mat[0].length, low = 0, high = n - 1, maxRow = -1;
		while (low < high) {
			int midColumn = (low + high) >> 1;
			maxRow = findMaxRowIndex(mat, midColumn);
			int rightInMaxRow = midColumn == n - 1 ? -1 : mat[maxRow][midColumn + 1];
			if (mat[maxRow][midColumn] < rightInMaxRow) {
				low = midColumn + 1;
			} else {
				high = midColumn;
			}
		}
		return new int[]{findMaxRowIndex(mat, low), low};
	}

	private int findMaxRowIndex(int[][] mat, int col) {
		int maxRowValue = -1, maxRow = -1;
		for (int i = 0; i < mat.length; i++) {
			if (mat[i][col] > maxRowValue) {
				maxRowValue = mat[i][col];
				maxRow = i;
			}
		}
		return maxRow;
	}
}
