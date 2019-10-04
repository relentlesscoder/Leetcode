package org.wshuai.leetcode;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by Wei on 10/3/19.
 * #1135 https://leetcode.com/problems/connecting-cities-with-minimum-cost/
 */
public class ConnectingCitiesWithMinimumCost {

  private boolean[] marked;
  private LinkedList<int[]>[] adj;
  private PriorityQueue<int[]> queue;

  // Prim's MST
  public int minimumCost(int N, int[][] connections) {
    int res = 0;
    adj = new LinkedList[N];
    for(int i = 0; i < N; i++){
      adj[i] = new LinkedList<int[]>();
    }
    for(int[] conn: connections){
      adj[conn[0] - 1].offer(new int[]{conn[0] - 1, conn[1] - 1, conn[2]});
      adj[conn[1] - 1].offer(new int[]{conn[1] - 1, conn[0] - 1, conn[2]});
    }
    marked = new boolean[N];
    queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
    visit(0);
    while(!queue.isEmpty()){
      int[] edge = queue.poll();
      int v = edge[0];
      int w = edge[1];
      if(marked[v] && marked[w]){
        continue;
      }
      res += edge[2];
      if(!marked[v]){
        visit(v);
      }
      if(!marked[w]){
        visit(w);
      }
    }
    for(boolean m: marked){
      if(!m){
        return -1;
      }
    }
    return res;
  }

  private void visit(int v){
    marked[v] = true;
    for(int[] edge: adj[v]){
      if(!marked[edge[1]]){
        queue.offer(edge);
      }
    }
  }
}
