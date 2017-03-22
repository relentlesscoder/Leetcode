package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 3/20/17.
 * #310 https://leetcode.com/problems/minimum-height-trees/
 */
public class MinimumHeightTrees {

  //O(n), see https://discuss.leetcode.com/topic/30572/share-some-thoughts/2
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    List<Integer> lst = new ArrayList<Integer>();
    if(n <= 0){
      return lst;
    }
    if(n == 1){
      lst.add(0);
      return lst;
    }
    List<Set<Integer>> adj = new ArrayList<Set<Integer>>();
    for(int i = 0; i < n; i++){
      adj.add(new HashSet<Integer>());
    }
    for(int i = 0; i < edges.length; i++){
      adj.get(edges[i][0]).add(edges[i][1]);
      adj.get(edges[i][1]).add(edges[i][0]);
    }
    for(int i = 0; i < n; i++){
      if(adj.get(i).size() == 1){
        lst.add(i);
      }
    }
    while(n > 2){
      n -= lst.size();
      List<Integer> nLst = new ArrayList<Integer>();
      for(int i: lst){
        int j = adj.get(i).iterator().next();
        adj.get(j).remove(i);
        if(adj.get(j).size() == 1){
          nLst.add(j);
        }
      }
      lst = nLst;
    }
    return lst;
  }

  //TLE
  public List<Integer> findMinHeightTreesNaive(int n, int[][] edges) {
    if(n <= 0){
      return new ArrayList<Integer>();
    }
    int min = Integer.MAX_VALUE;
    List<List<Integer>> adj = new ArrayList<List<Integer>>();
    for(int i = 0; i < n; i++){
      adj.add(new ArrayList<Integer>());
    }
    for(int i = 0; i < edges.length; i++){
      adj.get(edges[i][0]).add(edges[i][1]);
      adj.get(edges[i][1]).add(edges[i][0]);
    }
    List<Integer> lst = new ArrayList<Integer>();
    for(int i = 0; i < n; i++){
      int height = getTreeHight(i, adj);
      if(height == min){
        lst.add(i);
      }else if(height < min){
        lst = new ArrayList<Integer>();
        lst.add(i);
        min = height;
      }
    }
    return lst;
  }

  private int getTreeHight(int i, List<List<Integer>> adj){
    int res = 0;
    Set<Integer> visited = new HashSet<Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();
    Queue<Integer> next = new LinkedList<Integer>();
    queue.offer(i);
    visited.add(i);
    while(!queue.isEmpty()){
      int node = queue.poll();
      List<Integer> lst = adj.get(node);
      for(int j = 0; j < lst.size(); j++){
        int nnode = lst.get(j);
        if(!visited.contains(nnode)){
          visited.add(nnode);
          next.offer(nnode);
        }
      }
      if(queue.isEmpty()){
        res++;
        queue = next;
        next = new LinkedList<Integer>();
      }
    }
    return res-1;
  }
}
