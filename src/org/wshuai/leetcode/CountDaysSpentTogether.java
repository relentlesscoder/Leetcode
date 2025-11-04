package org.wshuai.leetcode;

/**
 * Created by Wei on 11/04/2025.
 * #2409 https://leetcode.com/problems/count-days-spent-together/
 */
public class CountDaysSpentTogether {

    private static final int[] DAYS = new int[] {31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};

    // time O(1), space O(1)
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveAliceDate = parseDate(arriveAlice);
        int leaveAliceDate = parseDate(leaveAlice);
        int arriveBobDate = parseDate(arriveBob);
        int leaveBobDate = parseDate(leaveBob);
        return Math.max(0, Math.min(leaveAliceDate, leaveBobDate) - Math.max(arriveAliceDate, arriveBobDate) + 1);
    }

    private int parseDate(String val) {
        String[] arr = val.split("-");
        int month = Integer.parseInt(arr[0]) - 1, day = Integer.parseInt(arr[1]);
        return (month >= 1 ? DAYS[month - 1] : 0) + day;
    }
}
