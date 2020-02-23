package org.wshuai.leetcode;

import java.util.HashMap;

/**
 * Created by Wei on 02/23/2020.
 * #1357 https://leetcode.com/problems/apply-discount-every-n-orders/
 */
public class ApplyDiscountEveryNOrders {
	private int count, n;
	private double disc;
	private HashMap<Integer, Integer> productPrices;

	public ApplyDiscountEveryNOrders(int n, int discount, int[] products, int[] prices) {
		count = 0;
		this.n = n;
		this.disc = 1.0 - discount / 100.0;
		productPrices = new HashMap<>();
		for(int i = 0; i < products.length; i++){
			productPrices.put(products[i], prices[i]);
		}
	}

	public double getBill(int[] product, int[] amount) {
		count++;
		double res = 0.0;
		for(int i = 0; i < product.length; i++){
			res += productPrices.get(product[i]) * amount[i];
		}
		if(count == n){
			res *= disc;
			count = 0;
		}
		return res;
	}
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */
