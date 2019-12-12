package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Wei on 12/12/2019.
 * #644 https://leetcode.com/problems/maximum-average-subarray-ii/
 */
public class GuessTheWord {

	// https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
	public void findSecretWord(String[] wordlist, Master master) {
		for (int i = 0, x = 0; i < 10 && x < 6; ++i) {
			HashMap<String, Integer> count = new HashMap<>();
			for (String w1 : wordlist)
				for (String w2 : wordlist)
					if (match(w1, w2) == 0)
						count.put(w1, count.getOrDefault(w1 , 0) + 1);
			int[] minimax = new int[]{-1, 1000};
			for (int k = 0; k < wordlist.length; k++){
				if (count.getOrDefault(wordlist[k], 0) < minimax[1]){
					minimax = new int[]{k, count.getOrDefault(wordlist[k], 0)};
				}
			}
			x = master.guess(wordlist[minimax[0]]);
			List<String> wordlist2 = new ArrayList<String>();
			for (String w : wordlist)
				if (match(wordlist[minimax[0]], w) == x)
					wordlist2.add(w);
			wordlist = wordlist2.toArray(new String[0]);
		}
	}

	public void findSecretWordNaive(String[] wordlist, Master master) {
		for(int i = 0, x = 0; i < 10 && x < 6; i++){
			String guess = wordlist[new Random().nextInt(wordlist.length)];
			x = master.guess(guess);
			List<String> wordlist2 = new ArrayList<>();
			for (String w : wordlist)
				if (match(guess, w) == x)
					wordlist2.add(w);
			wordlist = wordlist2.toArray(new String[wordlist2.size()]);
		}
	}

	private int match(String a, String b) {
		int matches = 0;
		for (int i = 0; i < a.length(); ++i) if (a.charAt(i) == b.charAt(i)) matches ++;
		return matches;
	}
}

 interface Master {
	public int guess(String word);
 }
