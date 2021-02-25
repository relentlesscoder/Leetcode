package org.wshuai.leetcode;

import java.util.PriorityQueue;

/**
 * Created by Wei on 02/25/2021.
 * #1738 https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/
 */
public class FindKthLargestXORCoordinateValue {

    // time O(m*n*log(k)), space O(k)
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                matrix[i][j] ^= matrix[i][j - 1];
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i != 0){
                    matrix[i][j] ^= matrix[i - 1][j];
                }
                pq.offer(matrix[i][j]);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
}
