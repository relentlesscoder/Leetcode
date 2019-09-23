package org.wshuai.leetcode;

/**
 * Created by Wei on 7/25/2017.
 * #551 https://leetcode.com/problems/student-attendance-record-i/
 */
public class StudentAttendanceRecordI {
	public boolean checkRecord(String s) {
		if (s == null || s.isEmpty()) {
			return false;
		}
		int len = s.length();
		int i = 0;
		int cnt = 0;
		while (i < len) {
			char c = s.charAt(i);
			if (c == 'A' && ++cnt >= 2) {
				return false;
			} else if (c == 'L' && i < len - 2 && s.charAt(i + 1) == 'L' && s.charAt(i + 2) == 'L') {
				return false;
			}
			i++;
		}
		return true;
	}
}
