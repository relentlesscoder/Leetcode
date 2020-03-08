package org.wshuai.leetcode;

/**
 * Created by Wei on 10/11/2019.
 * #0468 https://leetcode.com/problems/validate-ip-address/
 */
public class ValidateIPAddress {
	// time O(n)
	public String validIPAddress(String IP) {
		if(IP == null || IP.length() == 0){
			return "Neither";
		}
		if(IP.contains(":")){
			if(checkIPv6(IP)){
				return "IPv6";
			}
		}else{
			if(checkIPV4(IP)){
				return "IPv4";
			}
		}
		return "Neither";
	}

	private boolean checkIPV4(String ip){
		// https://stackoverflow.com/questions/14602062/java-string-split-removed-empty-values/14602155
		String[] vals = ip.split("\\.", -1);
		if(vals.length != 4){
			return false;
		}
		for(String v: vals){
			if(v.startsWith("0") && v.length() > 1){
				return false;
			}
			for(char c: v.toCharArray()){
				if(!isValidIPv4Character(c)){
					return false;
				}
			}
			try{
				int i = Integer.parseInt(v);
				if(i < 0 || i > 255){
					return false;
				}
			}catch(Exception ex){
				return false;
			}
		}
		return true;
	}

	private boolean checkIPv6(String ip){
		String[] vals = ip.split(":", -1);
		if(vals.length != 8){
			return false;
		}
		for(String v: vals){
			if(v.length() == 0 || v.length() > 4){
				return false;
			}
			for(char c: v.toCharArray()){
				if(!isValidIPv6Character(c)){
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidIPv6Character(char c){
		return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
	}

	private boolean isValidIPv4Character(char c){
		return (c >= '0' && c <= '9');
	}
}
