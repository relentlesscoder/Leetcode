package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/21/2019.
 * #0751 https://leetcode.com/problems/ip-to-cidr/
 */
public class IPToCIDR {
	public List<String> ipToCIDR(String ip, int n) {
		long x = 0;
		String[] ips = ip.split("\\.");
		for(int i = 0; i < ips.length; i++){
			x = Long.parseLong(ips[i]) + x * 256;
		}

		List<String> res = new ArrayList<>();
		while(n > 0){
			// this count value here means if we don't change the current start ip, how many
			// more ips we can represent with CIDR
			long count = x & -x;

			// for example count is 7 but we only want to 2 more ips
			while(count > n){
				count /= 2;
			}

			res.add(oneCIDR(x, count));
			n = n - (int)count;
			x = x + (int)count;
		}
		return res;
	}

	private String oneCIDR(long x, long count){
		int d, c, b, a;
		d = (int) x & 255; // Compute right-most part of ip
		x >>= 8; // throw away the right-most part of ip
		c = (int) x & 255;
		x >>= 8;
		b = (int) x & 255;
		x >>= 8;
		a = (int) x & 255;

		int len = 0;
		// this while loop to know how many digits of count is in binary
		// for example, 00001000 here the len will be 4.
		while(count > 0){
			count /= 2;
			len++;
		}
		int mask = 32 - (len - 1);
		// Think about 255.0.0.7 -> 11111111 00000000 00000000 00000111
		// x & -x of it is 00000001, the mask is 32. (which is 32 - (1 - 1))

		return new StringBuilder()
				.append(a)
				.append(".")
				.append(b)
				.append(".")
				.append(c)
				.append(".")
				.append(d)
				.append("/")
				.append(mask)
				.toString();
	}
}
