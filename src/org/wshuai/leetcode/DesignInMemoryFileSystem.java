package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/13/2019.
 * #588 https://leetcode.com/problems/design-in-memory-file-system/
 */
public class DesignInMemoryFileSystem {
	private Map<String, TreeSet<String>> directories;
	private Map<String, StringBuilder> files;

	public DesignInMemoryFileSystem() {
		directories = new HashMap<>();
		files = new HashMap<>();
		directories.put("/", new TreeSet<>());
	}

	public List<String> ls(String path) {
		if(files.containsKey(path)){
			int index = path.lastIndexOf("/");
			return Arrays.asList(path.substring(index + 1));
		}else{
			return new ArrayList<String>(directories.get(path + (path.equals("/") ? "" : "/")));
		}
	}

	public void mkdir(String path) {
		for(int i = 0; i < path.length(); i++){
			if(path.charAt(i) == '/'){
				String dir = path.substring(0, i + 1);
				directories.putIfAbsent(dir, new TreeSet<>());
				int next = path.indexOf("/", i + 1);
				next = next == -1 ? path.length() : next;
				directories.get(dir).add(path.substring(i + 1, next));
			}
		}
		directories.putIfAbsent(path + "/", new TreeSet<>());
	}

	public void addContentToFile(String filePath, String content) {
		int index = filePath.lastIndexOf("/");
		String dir = filePath.substring(0, index + 1);
		String fileName = filePath.substring(index + 1);
		directories.get(dir).add(fileName);
		files.putIfAbsent(filePath, new StringBuilder());
		files.get(filePath).append(content);
	}

	public String readContentFromFile(String filePath) {
		return files.get(filePath).toString();
	}
}
