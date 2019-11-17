package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 11/16/2019.
 * #1168 https://leetcode.com/problems/optimize-water-distribution-in-a-village/
 */
public class OptimizeWaterDistributionInAVillage {
	// Prim's MST
	public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
		int cost = 0;
		PriorityQueue<UndirectedWeightedEdge> queue = new PriorityQueue<>();
		Vertex[] graph = new Vertex[n + 1];
		Set<Integer> mst = new HashSet<>();
		for(int i = 0; i <= n; i++){
			graph[i] = new Vertex(i);
		}
		for(int i = 0; i < wells.length; i++){
			UndirectedWeightedEdge e = new UndirectedWeightedEdge(i + 1, wells[i]);
			graph[0].edges.add(e);
			queue.offer(e);
		}
		for(int[] p : pipes){
			graph[p[0]].edges.add(new UndirectedWeightedEdge(p[1], p[2]));
			graph[p[1]].edges.add(new UndirectedWeightedEdge(p[0], p[2]));
		}
		mst.add(0);
		while(!queue.isEmpty() && mst.size() < n + 1){
			UndirectedWeightedEdge next = queue.poll();
			if(mst.contains(next.v)){
				continue;
			}
			cost += next.w;
			Vertex vn = graph[next.v];
			mst.add(next.v);
			for(UndirectedWeightedEdge e : vn.edges){
				if(!mst.contains(e.v)){
					queue.offer(e);
				}
			}
		}
		return cost;
	}
}

class UndirectedWeightedEdge implements Comparable<UndirectedWeightedEdge>{

	int v;
	int w;

	public UndirectedWeightedEdge(int v, int w){
		this.v = v;
		this.w = w;
	}

	public int compareTo(UndirectedWeightedEdge e){
		return this.w - e.w;
	}
}

class Vertex{
	int id;
	List<UndirectedWeightedEdge> edges;

	public Vertex(int id){
		this.id = id;
		this.edges = new ArrayList<>();
	}
}
