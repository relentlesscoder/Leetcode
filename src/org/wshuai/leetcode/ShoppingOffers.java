package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 10/27/2019.
 * #0638 https://leetcode.com/problems/shopping-offers/
 */
public class ShoppingOffers {
	private int res;

	// time O((m*n)^h)
	// m is the number of items, n is the number of offers and
	// h is the height of the recursion tree (dependent on needs)
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		res = 0;
		for(int i = 0; i < price.size(); i++){
			res += price.get(i) * needs.get(i);
		}
		dfs(price, special, needs, 0);
		return res;
	}

	private void dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int paid){
		int valid = 0;
		for(int i = 0; i < special.size(); i++){
			List<Integer> offer = special.get(i);
			if(canApply(needs, offer)){
				applyOffer(needs, offer);
				dfs(price, special, needs, paid + offer.get(offer.size() - 1));
				revertOffer(needs, offer);
				valid++;
			}
		}
		// if no more applicable offer can be found, buy remaining items one by one by it's listing price
		if(valid == 0){
			for(int i = 0; i < needs.size(); i++){
				paid += price.get(i) * needs.get(i);
			}
			res = Math.min(res, paid);
			return;
		}
	}

	private boolean canApply(List<Integer> needs, List<Integer> offer){
		for(int i = 0; i < needs.size(); i++){
			if(offer.get(i) > needs.get(i)){
				return false;
			}
		}
		return true;
	}

	private void applyOffer(List<Integer> needs, List<Integer> offer){
		for(int i = 0; i < needs.size(); i++){
			needs.set(i, needs.get(i) - offer.get(i));
		}
	}

	private void revertOffer(List<Integer> needs, List<Integer> offer){
		for(int i = 0; i < needs.size(); i++){
			needs.set(i, needs.get(i) + offer.get(i));
		}
	}
}
