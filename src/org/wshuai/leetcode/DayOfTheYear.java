package org.wshuai.leetcode;

/**
 * Created by Wei on 8/19/19.
 * #1154 https://leetcode.com/problems/day-of-the-year/
 */
public class DayOfTheYear {
	public int dayOfYear(String date) {
		NumberOfDaysInAMonth nod = new NumberOfDaysInAMonth();
		String[] nums = date.split("-");
		int year = Integer.parseInt(nums[0]);
		int month = Integer.parseInt(nums[1]);
		int day = Integer.parseInt(nums[2]);
		int res = 0;
		for (int i = 1; i < month; i++) {
			res += nod.numberOfDays(year, i);
		}
		return res + day;
	}
}
