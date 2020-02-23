package org.wshuai.leetcode;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Created by Wei on 02/23/2020.
 * #1360 https://leetcode.com/problems/number-of-days-between-two-dates/
 */
public class NumberOfDaysBetweenTwoDates {
	// the worst question ever
	public int daysBetweenDates(String date1, String date2) {
		LocalDate dateBefore = LocalDate.parse(date1);
		LocalDate dateAfter = LocalDate.parse(date2);

		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		return (int)Math.abs(noOfDaysBetween);
	}
}
