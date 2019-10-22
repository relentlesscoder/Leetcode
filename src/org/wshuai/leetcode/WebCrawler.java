package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/21/19.
 * #1236 https://leetcode.com/problems/web-crawler/
 */
public class WebCrawler {

	// BFS
	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		String hostname = getHostName(startUrl);
		Set<String> visited = new HashSet<>();
		LinkedList<String> queue = new LinkedList<>();
		queue.offerLast(startUrl);
		while(!queue.isEmpty()){
			String url = queue.pollFirst();
			if(!url.contains(hostname) || visited.contains(url)){
				continue;
			}
			visited.add(url);
			List<String> next = htmlParser.getUrls(url);
			for(String s: next){
				queue.offerLast(s);
			}
		}
		return new ArrayList<String>(visited);
	}

	private String getHostName(String url){
		String[] vals = url.split("/");
		return vals[2];
	}
}

// This is the HtmlParser's API interface.
// You should not implement it, or speculate about its implementation
interface HtmlParser {
	List<String> getUrls(String url);
}
