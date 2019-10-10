package org.wshuai.leetcode;

/**
 * Created by Wei on 10/1/2019.
 * #1105 https://leetcode.com/problems/filling-bookcase-shelves/
 */
public class FillingBookcaseShelves {

	// https://www.youtube.com/watch?v=a7TLEVdqg0Q
	public int minHeightShelves(int[][] books, int shelf_width) {
		int[] dp = new int[books.length + 1];
		dp[0] = 0;

		for(int i = 1; i <= books.length; i++){
			int width = books[i - 1][0];
			int height = books[i - 1][1];
			// add a new level with only the book i
			dp[i] = dp[i - 1] + height;
			// within the shelf width limit, try to remove books from previous levels to the current
			for(int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; j--){
				height = Math.max(height, books[j - 1][1]);
				width += books[j - 1][0];
				dp[i] = Math.min(dp[i], dp[j - 1] + height);
			}
		}

		return dp[books.length];
	}

	private int res;
	private int[][] books;
	private int shelf_width;

	public int minHeightShelvesDFS(int[][] books, int shelf_width) {
		res = Integer.MAX_VALUE;
		this.books = books;
		this.shelf_width = shelf_width;
		dfs(0, 0, 0, 0);
		return res;
	}

	private void dfs(int start, int curr_width, int curr_height, int total_height){
		if(start >= books.length){
			res = Math.min(res, total_height);
			return;
		}

		int i = start;
		while(i < books.length && curr_width + books[i][0] <= shelf_width){
			curr_width += books[i][0];
			curr_height = Math.max(curr_height, books[i][1]);
			dfs(i + 1, 0, 0, total_height + curr_height);
			i++;
		}
	}
}
