package org.wshuai.leetcode;

/**
 * Created by Wei on 12/25/2019.
 * #0782 https://leetcode.com/problems/transform-to-chessboard/
 */
public class TransformToChessboard {
	// time O(N^2)
	// https://leetcode.com/problems/transform-to-chessboard/discuss/234984/C%2B%2B-solution-with-very-detailed-comments-especially-the-swap-counting-part
	public int movesToChessboard(int[][] board) {
		int N = board.length;
		int rowCount = 0;
		int colCount = 0;
		for(int i = 0; i < N; i++){
			rowCount += board[i][0] == 1 ? 1 : -1;
			for(int j = 0; j < N; j++){
				if(i == 0){
					colCount += board[i][j] == 1 ? 1 : -1;
				}
				if((board[i][0] ^ board[0][0] ^ board[i][j] ^ board[0][j]) == 1){
					return -1;
				}
			}
		}
		if(Math.abs(rowCount) > 1 || Math.abs(colCount) > 1){
			return -1;
		}
		int rowSwap = 0, colSwap = 0, rowZeroCount = 0, colZeroCount = 0;
		// When n is odd, we need fit the item whose count is larger into even positions because even position is more than odd position.
		// E.g.
		// 0,1,0,1,1, then 0s must stay on odd positions so that 1s stay on even positions: 1,0,1,0,1.
		// 1,0,1,0,0, then 1s must stay on odd positions so that 0s stay on even positions: 0,1,0,1,0.
		for(int i = 0; i < N; i++){
			if(i % 2 == 1){
				// Assume 0 is less and should stay on odd position, so we swap 1 away from odd position.
				rowSwap += board[i][0];
				colSwap += board[0][i];
			}
			rowZeroCount += board[i][0] == 0 ? 1 : 0;
			colZeroCount += board[0][i] == 0 ? 1 : 0;
		}
		int oddPositionCount = N / 2;
		if(N % 2 == 1){
			// Count of 0 == odd_position_count means 0 is less, so we're right on swapping 1 away, the current swap count is correct.
			// Otherwise we should keep 1 on the odd position and swap 0 away, so the swap count becomes odd_position_count - row_swap_count.
			rowSwap = rowZeroCount == oddPositionCount ? rowSwap : oddPositionCount - rowSwap;
			colSwap = colZeroCount == oddPositionCount ? colSwap : oddPositionCount - colSwap;
		}else{
			// If n is even, odd position's count is the same with even position's count, choose whichever swap count is smaller.
			rowSwap = Math.min(rowSwap, oddPositionCount - rowSwap);
			colSwap = Math.min(colSwap, oddPositionCount - colSwap);
		}
		return rowSwap + colSwap;
	}
}
