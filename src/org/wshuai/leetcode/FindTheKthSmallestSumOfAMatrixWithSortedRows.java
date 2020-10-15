package org.wshuai.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Wei on 05/03/2020.
 * #1439 https://leetcode.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/
 */
public class FindTheKthSmallestSumOfAMatrixWithSortedRows {

    // time O(k*m*n), space O(k*n)
    /*We'll do at most k n insertions, so each PQ operation is O(log k + log n).
    With the O(m) copies and hashing, the overall complexity is O(k m + k n m + k n log k + k n log n)).
    O(k n m) dominates in the worst case.*/
    public int kthSmallestBFS(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[] sum = new int[m + 1];
        for(int i = 0; i < m; i++){
            sum[0] += mat[i][0];
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<String> visited = new HashSet<>();
        pq.offer(sum);
        visited.add(Arrays.toString(sum));
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(--k == 0){
                return cur[0];
            }
            for(int i = 1; i <= m; i++){
                if(cur[i] == n - 1){
                    continue;
                }
                int[] next = cur.clone();
                next[0] = next[0] - mat[i - 1][next[i]] + mat[i - 1][next[i] + 1];
                next[i]++;
                if(visited.add(Arrays.toString(next))){
                    pq.offer(next);
                }
            }
        }
        return -1;
    }

    // time O(m*n*k*log(k)), space O(m*k)
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i = 0; i < n; i++){
            pq.offer(mat[0][i]);
            if(pq.size() > k){
                pq.poll();
            }
        }
        for(int i = 1; i < m; i++){
            PriorityQueue<Integer> next = new PriorityQueue<>((a, b) -> b - a);
            for(int sum : pq){
                for(int j = 0; j < n; j++){
                    next.offer(sum + mat[i][j]);
                    if(next.size() > k){
                        next.poll();
                    }
                }
            }
            pq = next;
        }
        return pq.peek();
    }
}
