package org.wshuai.algorithm.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 9/24/2016.
 */
public class Graph {
  private static final String NEWLINE = System.getProperty("line.separator");

  private final int V;
  private int E;
  private List<Integer>[] adj;

  public Graph(int V){
    if(V < 0){
      throw new IllegalArgumentException("Number of vertices much be nonnegative");
    }
    this.V = V;
    this.E = 0;
    adj = (ArrayList<Integer>[])new List[V];
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
    adj[w].add(v);
  }

  public Iterable<Integer> adj(int v){
    validateVertex(v);
    return adj[v];
  }

  public int degree(int v){
    validateVertex(v);
    return adj[v].size();
  }

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
