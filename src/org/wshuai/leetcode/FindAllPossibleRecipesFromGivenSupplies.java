package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 09/18/2023.
 * #2115 https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/
 */
public class FindAllPossibleRecipesFromGivenSupplies {

	// time O(V + E), space O(V + E)
	public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
		List<String> res = new ArrayList<>();
		Map<String, List<String>> graph = new HashMap<>();
		Map<String, Integer> degree = new HashMap<>();
		for (int i = 0; i < recipes.length; i++) {
			for (String ingredient : ingredients.get(i)) {
				graph.putIfAbsent(ingredient, new ArrayList<>());
				graph.get(ingredient).add(recipes[i]);
			}
			degree.put(recipes[i], ingredients.get(i).size());
		}
		LinkedList<String> queue = new LinkedList<>();
		for (String supply : supplies) {
			queue.offerLast(supply);
		}
		while (!queue.isEmpty()) {
			String curr = queue.pollFirst();
			if (graph.containsKey(curr)) {
				for (String next : graph.get(curr)) {
					degree.put(next, degree.get(next) - 1);
					if (degree.get(next) == 0) {
						res.add(next);
						queue.offer(next);
					}
				}
			}
		}
		return res;
	}
}
