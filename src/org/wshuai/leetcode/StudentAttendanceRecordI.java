package org.wshuai.leetcode;

/**
 * Created by Wei on 07/25/2017.
 * #0551 https://leetcode.com/problems/student-attendance-record-i/
 */
public class StudentAttendanceRecordI {
	// time O(n)
	public boolean checkRecord(String s) {
		if(s == null || s.isEmpty()){
			return true;
		}
		char[] arr = s.toCharArray();
		int n = arr.length, absent = 0;
		for(int i = 0; i < n; i++){
			if(arr[i] == 'A' && ++absent > 1){
				return false;
			}
			if(arr[i] == 'L' && i < n - 2 && arr[i + 1] == 'L' && arr[i + 2] == 'L'){
				return false;
			}
		}
		return true;
	}
}
