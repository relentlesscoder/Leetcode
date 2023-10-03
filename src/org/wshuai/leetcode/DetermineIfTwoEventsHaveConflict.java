package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2446 https://leetcode.com/problems/determine-if-two-events-have-conflict/
 */
public class DetermineIfTwoEventsHaveConflict {

	// time O(1), space O(1)
	public boolean haveConflict(String[] event1, String[] event2) {
		int[] event1TimeInMinutes = new int[2], event2TimeInMinutes = new int[2];
		event1TimeInMinutes[0] = Integer.parseInt(event1[0].substring(0, 2)) * 60 + Integer.parseInt(event1[0].substring(3));
		event1TimeInMinutes[1] = Integer.parseInt(event1[1].substring(0, 2)) * 60 + Integer.parseInt(event1[1].substring(3));
		event2TimeInMinutes[0] = Integer.parseInt(event2[0].substring(0, 2)) * 60 + Integer.parseInt(event2[0].substring(3));
		event2TimeInMinutes[1] = Integer.parseInt(event2[1].substring(0, 2)) * 60 + Integer.parseInt(event2[1].substring(3));
		return (event2TimeInMinutes[0] >= event1TimeInMinutes[0] && event2TimeInMinutes[0] <= event1TimeInMinutes[1]) ||
				(event2TimeInMinutes[1] >= event1TimeInMinutes[0] && event2TimeInMinutes[1] <= event1TimeInMinutes[1]) ||
				(event2TimeInMinutes[0] <= event1TimeInMinutes[0] && event2TimeInMinutes[1] >= event1TimeInMinutes[1]) ||
				(event1TimeInMinutes[0] <= event2TimeInMinutes[0] && event1TimeInMinutes[1] >= event2TimeInMinutes[1]);

	}
}
