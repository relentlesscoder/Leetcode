package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/10/16.
 * #133 https://leetcode.com/problems/clone-graph/
 */
public class CloneGraph {
  public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    if(node == null){
      return null;
    }

    Map<UndirectedGraphNode, UndirectedGraphNode> map =
      new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
    Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    queue.offer(node);
    UndirectedGraphNode root = new UndirectedGraphNode(node.label);
    map.put(node, root);
    while(!queue.isEmpty()){
      UndirectedGraphNode old = queue.poll();
      Iterator<UndirectedGraphNode> it = old.neighbors.listIterator();
      while(it.hasNext()){
        UndirectedGraphNode nxt = it.next();
        if(!map.containsKey(nxt)){
          UndirectedGraphNode clone = new UndirectedGraphNode(nxt.label);
          map.put(nxt, clone);
          map.get(old).neighbors.add(clone);
          queue.offer(nxt);
        }else{
          map.get(old).neighbors.add(map.get(nxt));
        }
      }
    }

    return root;
  }
}

/**
 * Definition for undirected graph.
*/
class UndirectedGraphNode {
  int label;

  List<UndirectedGraphNode> neighbors;

  UndirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<UndirectedGraphNode>();
  }
}

