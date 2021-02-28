package org.wshuai.leetcode;

import java.util.List;

/**
 * Created by Wei on 02/28/2021.
 * #1773 https://leetcode.com/problems/count-items-matching-a-rule/
 */
public class CountItemsMatchingARule {

	// time O(n)
	public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
		int res = 0, index = ruleKey.equals("type") ?
			0 : ruleKey.equals("color") ? 1 : 2;
		for(List<String> item : items){
			String val = item.get(index);
			if(ruleValue.equals(val)){
				res++;
			}
		}
		return res;
	}
}
