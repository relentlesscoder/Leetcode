package org.wshuai.leetcode;

/**
 * Created by Wei on 11/6/19.
 * #831 https://leetcode.com/problems/masking-personal-information/
 */
public class MaskingPersonalInformation {
	public String maskPII(String S) {
		if(S.contains("@")){
			return formatEmail(S.toLowerCase());
		}else{
			return formatPhone(S);
		}
	}

	private String formatEmail(String email){
		String[] arr = email.split("@");
		String name1 = arr[0];
		String masked = name1.charAt(0) + "*****" + name1.charAt(name1.length() - 1);
		return masked + "@" + arr[1];
	}

	private String formatPhone(String phone){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(i < phone.length()){
			char curr = phone.charAt(i);
			if(Character.isDigit(curr)){
				sb.append(curr);
			}
			i++;
		}
		String num = sb.toString();
		sb = new StringBuilder();
		int len = num.length();
		if(len > 10){
			sb.append("+");
			int cnt = len - 10;
			while(cnt-- > 0){
				sb.append("*");
			}
			sb.append("-");
		}
		sb.append("***-***-");
		sb.append(num.substring(len - 4));
		return sb.toString();
	}
}
