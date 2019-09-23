package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Wei on 4/8/17.
 * #535 https://leetcode.com/problems/encode-and-decode-tinyurl/
 */
public class EncodeAndDecodeTinyURL {
	private Map<String, String> map = new HashMap<String, String>();
	private Map<String, String> revMap = new HashMap<String, String>();
	private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int SEED = 62;
	private static final String BASE_URL = "http://tinyurl.com/";

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		if (longUrl == null || longUrl.isEmpty()) {
			return "";
		}
		if (revMap.containsKey(longUrl)) {
			return BASE_URL + revMap.get(longUrl);
		}
		Random rand = new Random();
		String key = "";
		do {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				int index = rand.nextInt(SEED);
				sb.append(CHAR_SET.charAt(index));
			}
			key = sb.toString();
		} while (map.containsKey(key));
		map.put(key, longUrl);
		revMap.put(longUrl, key);
		return BASE_URL + key;
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		if (shortUrl == null) {
			return "";
		}
		return map.getOrDefault(shortUrl.replace(BASE_URL, ""), "");
	}
}
