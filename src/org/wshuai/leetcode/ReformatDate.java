package org.wshuai.leetcode;

/**
 * Created by Wei on 07/12/2020.
 * #1507 https://leetcode.com/problems/reformat-date/
 */
public class ReformatDate {

	public String reformatDate(String date) {
		String[] strs = date.split("\\s+");
		String res = strs[2];
		res += parseMonth(strs[1]);
		res += parseDay(strs[0]);
		return res;
	}

	private String parseDay(String day){
		int num = Integer.parseInt(day.substring(0, day.length() - 2));
		return "-" + String.format("%02d", num);
	}

	private String parseMonth(String month){
		String res = "-";
		switch(month){
			case "Jan":
				res += "01";
				break;
			case "Feb":
				res += "02";
				break;
			case "Mar":
				res += "03";
				break;
			case "Apr":
				res += "04";
				break;
			case "May":
				res += "05";
				break;
			case "Jun":
				res += "06";
				break;
			case "Jul":
				res += "07";
				break;
			case "Aug":
				res += "08";
				break;
			case "Sep":
				res += "09";
				break;
			case "Oct":
				res += "10";
				break;
			case "Nov":
				res += "11";
				break;
			default:
				res += "12";
				break;
		}
		return res;
	}
}
