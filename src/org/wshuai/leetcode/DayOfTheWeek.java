package org.wshuai.leetcode;

/**
 * Created by Wei on 9/11/2019.
 * #1185 https://leetcode.com/problems/day-of-the-week/
 */
public class DayOfTheWeek {

    public String dayOfTheWeek(int day, int month, int year) {
        String[] arr = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int c = year / 100;
        year = year % 100;
        int w = (c / 4 - 2 * c + year + year / 4 + 13 * (month + 1) / 5 + day - 1) % 7;
        return arr[(w + 7) % 7];
    }

}
