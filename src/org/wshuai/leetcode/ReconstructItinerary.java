package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 2/20/17.
 * #332 https://leetcode.com/problems/reconstruct-itinerary/
 */
public class ReconstructItinerary {
  //Graph DFS
  public List<String> findItinerary(String[][] tickets) {
    List<String> res = new ArrayList<String>();
    if(tickets == null || tickets.length == 0){
      return res;
    }
    int len = tickets.length;
    Map<String, PriorityQueue<String>> adj = new HashMap<String, PriorityQueue<String>>();
    for(int i = 0; i < len; i++){
      String[] loc = tickets[i];
      adj.putIfAbsent(loc[0], new PriorityQueue<String>());
      adj.get(loc[0]).add(loc[1]);
    }
    findItineraryUtil("JFK", adj, res);
    return res;
  }

  private void findItineraryUtil(String dep, Map<String, PriorityQueue<String>> adj, List<String> res){
    PriorityQueue<String> arrs = adj.get(dep);
    while(arrs != null && !arrs.isEmpty()){
      findItineraryUtil(arrs.poll(), adj, res);
    }
    res.add(0, dep);
  }
}
