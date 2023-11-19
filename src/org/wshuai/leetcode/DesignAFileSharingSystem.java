package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 07/12/2020.
 * #1500 https://leetcode.com/problems/design-a-file-sharing-system/
 */
public class DesignAFileSharingSystem {

	private int maxId;
	private PriorityQueue<Integer> freeIds;
	private Map<Integer, Set<Integer>> userChunks;
	private Map<Integer, Set<Integer>> chunkOwners;

	public DesignAFileSharingSystem(int m) {
		maxId = 0;
		freeIds = new PriorityQueue<>();
		userChunks = new HashMap<>();
		chunkOwners = new HashMap<>();
	}

	public int join(List<Integer> ownedChunks) {
		int uid = freeIds.isEmpty() ? ++maxId : freeIds.poll();
		userChunks.put(uid, new HashSet<>(ownedChunks));
		for(int c : ownedChunks){
			chunkOwners.putIfAbsent(c, new TreeSet<>());
			chunkOwners.get(c).add(uid);
		}
		return uid;
	}

	public void leave(int userID) {
		if(!userChunks.containsKey(userID)){
			return;
		}
		Set<Integer> chunks = userChunks.get(userID);
		for(int c : chunks){
			chunkOwners.get(c).remove(userID);
		}
		userChunks.remove(userID);
		freeIds.offer(userID);
	}

	public List<Integer> request(int userID, int chunkID) {
		List<Integer> res = new ArrayList<>();
		if(!userChunks.containsKey(userID)){
			return res;
		}
		if(!chunkOwners.containsKey(chunkID) || chunkOwners.get(chunkID).size() == 0){
			return res;
		}
		Set<Integer> owners = chunkOwners.get(chunkID);
		res.addAll(owners);
		owners.add(userID);
		userChunks.get(userID).add(chunkID);
		return res;
	}
}

/**
 * Your FileSharing object will be instantiated and called as such:
 * FileSharing obj = new FileSharing(m);
 * int param_1 = obj.join(ownedChunks);
 * obj.leave(userID);
 * List<Integer> param_3 = obj.request(userID,chunkID);
 */
