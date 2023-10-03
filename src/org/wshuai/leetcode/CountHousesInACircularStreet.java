package org.wshuai.leetcode;

/**
 * Created by Wei on 10/02/2023.
 * #2728 https://leetcode.com/problems/count-houses-in-a-circular-street/
 */
public class CountHousesInACircularStreet {

	// time O(k + n), space O(1)
	public int houseCount(Street street, int k) {
		int res = 0;
		while (k-- > 0) {
			street.closeDoor();
			street.moveRight();
		}
		street.openDoor();
		street.moveRight();
		while (!street.isDoorOpen()) {
			res++;
			street.moveRight();
		}
		return res + 1;
	}

	/**
	 * Definition for a street.
	 */
	private interface Street {
		public void openDoor();

		public void closeDoor();

		public boolean isDoorOpen();

		public void moveRight();

		public void moveLeft();
	}

}
