package org.wshuai.leetcode;

/**
 * Created by Wei on 09/27/2020.
 * #1598 https://leetcode.com/problems/crawler-log-folder/
 */
public class CrawlerLogFolder {

	// time O(n)
	public int minOperations(String[] logs) {
		int level = 0;
		for(int i = 0; i < logs.length; i++){
			if(logs[i].equals("./")){
				continue;
			}else if(logs[i].equals("../")){
				level = level == 0 ? 0 : level - 1;
			}else{
				level++;
			}
		}
		return level;
	}
}
