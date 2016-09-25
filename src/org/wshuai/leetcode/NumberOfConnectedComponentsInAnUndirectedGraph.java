package org.wshuai.leetcode;

/**
 * Created by Wei on 9/24/2016.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {
  public int countComponents(int n, int[][] edges) {
    if(n <= 0){
      return 0;
    }
    if(edges == null || edges.length == 0){
      return n;
    }
    int len = edges.length;
    int count = 0;
    int[] visited = new int[n];
    for(int i = 0; i < n; i++){
      if(visited[i] != 1){
        countComponentsDFS(i, edges, len, visited);
        count++;
      }
    }
    return count;
  }

  private void countComponentsDFS(int v, int[][] edges, int len, int[] visited){
    visited[v] = 1;
    for(int j = 0; j < len; j++){
      int[] edge = edges[j];
      int fst = edge[0];
      int sec = edge[1];
      if(fst == v || sec == v){
        int vertex = fst == v ? sec : fst;
        if(visited[vertex] != 1){
          countComponentsDFS(vertex, edges, len, visited);
        }
      }
    }
  }
}
