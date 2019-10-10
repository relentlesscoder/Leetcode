package org.wshuai.leetcode;

/**
 * Created by Wei on 8/6/19.
 * #393 https://leetcode.com/problems/utf-8-validation/
 */
public class UTF8Validation {
	public boolean validUtf8(int[] data) {

		int numberOfBytesToProcess = 0;

		for (int i = 0; i < data.length; i++) {

			String bin = Integer.toBinaryString(data[i]);
			bin = bin.length() >= 8 ? bin.substring(bin.length() - 8) : "00000000".substring(bin.length() % 8) + bin;

			if (numberOfBytesToProcess == 0) {
				int j = 0;
				while (j < 8 && bin.charAt(j) != '0') {
					j++;
					numberOfBytesToProcess++;
				}

				// 1 byte characters
				if (numberOfBytesToProcess == 0) {
					continue;
				}

				if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
					return false;
				}

				// next n-1 bytes needs to start with "10"
				numberOfBytesToProcess--;
			} else {
				if (!(bin.charAt(0) == '1' && bin.charAt(1) == '0')) {
					return false;
				}
				numberOfBytesToProcess--;
			}
		}
		return numberOfBytesToProcess == 0;
	}
}
