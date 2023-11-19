package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 08/03/2020.
 * #1489 https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/
 */
public class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {

    // time O(E^2*log(E))
    // https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/discuss/697750/Java-Simple-Solution-based-on-finding-MST
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Map<int[], Integer> map = new HashMap<>();
        for(int i = 0; i < edges.length; i++){
            map.put(edges[i], i);
        }
        List<Integer> criticals = new ArrayList<>(), pseudos = new ArrayList<>();
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        int minCost = buildMST(edges, n, null, null);
        for(int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            int index = map.get(edge);
            if(buildMST(edges, n, null, edge) > minCost){
                criticals.add(index);
            }else if(buildMST(edges, n, edge, null) == minCost){
                pseudos.add(index);
            }
        }
        return Arrays.asList(criticals, pseudos);
    }

    private int buildMST(int[][] edges, int n, int[] pick, int[] skip){
        int cost = 0, group = n;
        int[] root = new int[n];
        for(int i = 0; i < n; i++){
            root[i] = i;
        }
        if(pick != null){
            union(root, pick[0], pick[1]);
            group--;
            cost += pick[2];
        }
        for(int i = 0; i < edges.length; i++){
            if(edges[i] != skip && union(root, edges[i][0], edges[i][1])){
                group--;
                cost += edges[i][2];
            }
        }
        return group == 1 ? cost : Integer.MAX_VALUE;
    }

    private boolean union(int[] root, int u, int v){
        int ru = find(root, u);
        int rv = find(root, v);
        if(ru == rv){
            return false;
        }
        root[ru] = rv;
        return true;
    }

    private int find(int[] root, int v){
        if(root[v] != v){
            root[v] = find(root, root[v]);
        }
        return root[v];
    }
}
