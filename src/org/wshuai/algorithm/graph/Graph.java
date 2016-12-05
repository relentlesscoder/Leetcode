package org.wshuai.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Wei on 9/24/2016.
 */
public class Graph {
  private static final String NEWLINE = System.getProperty("line.separator");

  private final int V;
  private int E;
  private List<Integer>[] adj;

  // Constructor
  public Graph(int V){
    if(V < 0){
      throw new IllegalArgumentException("Number of vertices much be nonnegative");
    }
    this.V = V;
    this.E = 0;
    adj = (ArrayList<Integer>[])new ArrayList[V];
    for(int v = 0; v < V; v++){
      adj[v] = new ArrayList<Integer>();
    }
  }

  public int V(){
    return V;
  }

  public int E(){
    return E;
  }

  private void validateVertex(int v){
    if(v < 0 || v >= V){
      throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }
  }

  public void addEdge(int v, int w){
    validateVertex(v);
    validateVertex(w);
    E++;
    adj[v].add(w);
    //adj[w].add(v);
  }

  public Iterable<Integer> adj(int v){
    validateVertex(v);
    return adj[v];
  }

  public int degree(int v){
    validateVertex(v);
    return adj[v].size();
  }

  public List<Integer> topologicalSort(){
    List<Integer> lst = new ArrayList<Integer>();
    Stack<Integer> stack = new Stack<Integer>();
    boolean[] visited = new boolean[V];
    Arrays.fill(visited, false);
    for(int i = 0; i < V; i++){
      if(!visited[i]){
        topologicalSortUtil(i, visited, stack);
      }
    }
    while (!stack.isEmpty()){
      lst.add(stack.pop());
    }
    return lst;
  }

  private void topologicalSortUtil(int i, boolean[] visited,
                                  Stack<Integer> stack){
    visited[i] = true;

    for(int n: adj[i]){
      if(!visited[n]){
        topologicalSortUtil(n, visited, stack);
      }
    }

    stack.push(i);
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(V + " vertices, " + E + " edges " + NEWLINE);
    for(int v = 0; v < V; v++){
      sb.append(v + ": ");
      for(int w : adj[v]){
        sb.append(w + " ");
      }
    }
    return sb.toString();
  }
}
