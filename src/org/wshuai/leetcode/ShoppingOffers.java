package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/27/19.
 * #638 https://leetcode.com/problems/shopping-offers/
 */
public class ShoppingOffers {
	private int res;

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		int N = price.size();
		res = 0;
		for(int i = 0; i < N; i++){
			res += needs.get(i) * price.get(i);
		}
		dfs(price, special, needs, 0);
		return res;
	}

	private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int paid){
		int found = 0;
		for(List<Integer> s: special){
			if(isValid(s, needs)){
				applyOffer(s, needs);
				dfs(price, special, needs, paid + s.get(s.size() - 1));
				revertOffer(s, needs);
				found++;
			}
		}
		if(found == 0){
			for(int i = 0; i < needs.size(); i++){
				paid += needs.get(i) * price.get(i);
			}
			res = Math.min(paid, res);
			return;
		}
	}

	private boolean isValid(List<Integer> offer, List<Integer> needs){
		for(int i = 0; i < needs.size(); i++){
			if(offer.get(i) > needs.get(i)){
				return false;
			}
		}
		return true;
	}

	private void applyOffer(List<Integer> offer, List<Integer> needs){
		for(int i = 0; i < needs.size(); i++){
			needs.set(i, needs.get(i) - offer.get(i));
		}
	}

	private void revertOffer(List<Integer> offer, List<Integer> needs){
		for(int i = 0; i < needs.size(); i++){
			needs.set(i, needs.get(i) + offer.get(i));
		}
	}
}
