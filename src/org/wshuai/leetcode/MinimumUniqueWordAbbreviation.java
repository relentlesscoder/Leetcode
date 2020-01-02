package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 12/31/2019.
 * #411 https://leetcode.com/problems/minimum-unique-word-abbreviation/
 */
public class MinimumUniqueWordAbbreviation {
	//N represents the length of the target string
	//Cand is the bitwise OR of all the bit conversion of each of the strings in dictionary
	//bn = (1 << n)
	//minab = One possible solution of a bit subset/ abbreviation of min lnegth
	//minLen = the length of a word abbreviation of min length
	int n, cand, bn, minLen, minab;
	//List of all the words in their binary converted form
	List<Integer> dict = new ArrayList<>();

	/*Logic of this method:
	 Whenever you have a sequence of k consecutive 0's you can replace them with a number.
	 You gain k characters, but then you lose 1 from adding the number, therefore the actual length
	 is k-1. We do this count locally however by viewing adjacent bits in the binary number*/
	public int abbrLen(int mask){
		int count = n; //All of the characters are turned on
		//3 = 0b11
		for (int b = 3; b < bn; b <<= 1){
			if ((mask & b) == 0) count--;
		}

		return count;
	}

	//Here usually for dfs we would track the position in the binary number we want to set.
	//In this implementation they just set the 1 already for us in advance

	void dfs(int bit, int mask){
		// System.out.println("dfs(" + Integer.toBinaryString(mask) + ")");
		//Compute the actual length of the abbreviation
		int len = abbrLen(mask);
		//Some smart pruning. (Don't even bother looking at possible abbreviations that are suboptimal)
		if (len >= minLen) return;
		//Assume that this bitmask(subset of target) that we are testing out will work
		boolean match = true;
		for (Integer d : dict){
			//Remember we are trying to check if for this mask we have at least 1 conflict
			//If we have at least 1 conflict with each string then this means this abbreviation can
			//be considered to be unique
			if ((mask & d) == 0){
				match = false;
				break; //We can break out here because don't have to find all possible abbreviations
			}
		}
		//New candidate (update)
		if (match){
			minLen = len; //If we have found a proper cover then update the min value
			minab = mask;
		}else {
			//Bit is a binary number with 1 bit set
			for (int b = bit; b < bn; b <<= 1){
				if ((cand & b) != 0) dfs(b << 1, mask + b);
			}
		}
	}

	public String minAbbreviation(String target, String[] dictionary) {

		n = target.length();
		bn = 1 << n;
		cand = 0;
		minLen = Integer.MAX_VALUE;
		StringBuilder res = new StringBuilder();

		//Preprocessing where we convert each string to a "match" binary number
		for (String s : dictionary){
			int word = 0; //This will be our integer match binary number
			//The only strings that can have conflicting abbr are ones of the same length
			if (s.length() != n) continue;
			for (int i = 0; i < n; i++)
				if (target.charAt(i) != s.charAt(i)) word |= 1 << i;
			//Add to converted integer dictionary list.
			dict.add(word);
			//Add to bit set of bitwise OR'ed candidate 1's list. (Use for optimization in dfs)
			cand |= word;
		}

		dfs(1, 0); //Now perform the bfs. Initial state (0b1, 0) (msb, mask)

		for(int i = 0; i < n;){
			if ((minab & (1 << i)) != 0){
				//If we have a 1 set, that means to keep this character
				res.append(target.charAt(i++));
			}else{//Otherwise we potentially have consecutive 0's so count them and replace it with the numebr
				int j = i;
				while (i < n && (minab & (1 << i)) == 0) i++;
				res.append(i-j);
			}
		}

		return res.toString();
	}
}
