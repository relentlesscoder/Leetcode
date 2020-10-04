package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2020.
 * #1603 https://leetcode.com/problems/design-parking-system/
 */
public class DesignParkingSystem {

	private int[] capacity;

	public DesignParkingSystem(int big, int medium, int small) {
		capacity = new int[]{big, medium, small};
	}

	public boolean addCar(int carType) {
		if(capacity[carType - 1] == 0){
			return false;
		}
		capacity[carType - 1]--;
		return true;
	}
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
