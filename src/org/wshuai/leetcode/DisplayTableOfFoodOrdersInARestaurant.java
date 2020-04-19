package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 04/19/2020.
 * #1418 https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/
 */
public class DisplayTableOfFoodOrdersInARestaurant {

	// time O(n*log(n)), space O(n)
	public List<List<String>> displayTable(List<List<String>> orders) {
		TreeMap<Integer, Table> tables = new TreeMap<>();
		TreeSet<String> foodNames = new TreeSet<>();
		for(List<String> orderDetail : orders){
			int tableId = Integer.parseInt(orderDetail.get(1));
			String food = orderDetail.get(2);
			foodNames.add(food);
			tables.putIfAbsent(tableId, new Table(tableId));
			tables.get(tableId).addOrder(food);
		}
		List<List<String>> res = new ArrayList<>();
		List<String> header = new ArrayList<>();
		header.add("Table");
		for(String f : foodNames){
			header.add(f);
		}
		res.add(header);
		for(Table cur : tables.values()){
			List<String> list = new ArrayList<>();
			list.add(String.valueOf(cur.id));
			for(String f : foodNames){
				list.add(String.valueOf(cur.order.getOrDefault(f, 0)));
			}
			res.add(list);
		}
		return res;
	}

	private class Table{
		int id;

		int total;

		Map<String, Integer> order;

		public Table(int id){
			this.id = id;
			total = 0;
			order = new HashMap<>();
		}

		public void addOrder(String food){
			order.put(food, order.getOrDefault(food, 0) + 1);
			total++;
		}
	}
}
