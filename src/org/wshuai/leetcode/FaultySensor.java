package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2023.
 * #1826 https://leetcode.com/problems/faulty-sensor/
 */
public class FaultySensor {

	// time O(n), space O(1)
	public int badSensor(int[] sensor1, int[] sensor2) {
		boolean test1 = detect(sensor1, sensor2), test2 = detect(sensor2, sensor1);
		if (test1 == test2) { // example [2,2,2,2,2] and [2,2,2,2,5]
			return -1;
		}
		return test1 ? 1 : 2;
	}

	private boolean detect(int[] sensor1, int[] sensor2) { // If we exclude the last index of the faulty sensor, it should be subsequence for the good sensor. But it should be false otherwise.
		int n = sensor1.length, i = 0;
		for (int j = 0; i < n - 1 && j < n; j++) {
			if (sensor1[i] == sensor2[j]) {
				i++;
			}
		}
		return (i == n - 1);
	}
}
