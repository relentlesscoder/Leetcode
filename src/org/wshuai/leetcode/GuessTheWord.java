package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/12/2019.
 * #0843 https://leetcode.com/problems/guess-the-word/
 */
public class GuessTheWord {

	// time O(n^2), space O(n)
	// https://leetcode.com/problems/guess-the-word/discuss/133862/Random-Guess-and-Minimax-Guess-with-Comparison
	public void findSecretWord(String[] wordlist, Master master) {
		for(int i = 0, x = 0; i < 10 && x < 6; i++){
			// try to use the word has fewest 0 matches with the rest word in
			// the list, which means has match characters with most of the other
			// words so we can filter out more words in one round
			Map<String, Integer> count = new HashMap<>();
			for(String w1 : wordlist){
				for(String w2 : wordlist){
					if(match(w1, w2) == 0){
						count.put(w1, count.getOrDefault(w1, 0) + 1);
					}
				}
			}
			String guess = "";
			int min = 1_000;
			for(String w : wordlist){
				if(count.getOrDefault(w, 0) < min){
					min = count.getOrDefault(w, 0);
					guess = w;
				}
			}
			// if the guess has x matched character with the final word,
			// for all the candidates eligible for the next round must
			// has x match with the current guess
			x = master.guess(guess);
			List<String> next = new ArrayList<>();
			for(String word : wordlist){
				if(match(guess, word) == x){
					next.add(word);
				}
			}
			wordlist = next.toArray(new String[next.size()]);
		}
	}

	// time O(n), space O(n)
	public void findSecretWordRandom(String[] wordlist, Master master) {
		for(int i = 0, x = 0; i < 10 && x < 6; i++){
			String guess = wordlist[new Random().nextInt(wordlist.length)];
			x = master.guess(guess);
			List<String> next = new ArrayList<>();
			for(String word : wordlist){
				if(match(guess, word) == x){
					next.add(word);
				}
			}
			wordlist = next.toArray(new String[next.size()]);
		}
	}

	private int match(String S, String T){
		int matched = 0;
		for(int i = 0; i < 6; i++){
			if(S.charAt(i) == T.charAt(i)){
				matched++;
			}
		}
		return matched;
	}

	private interface Master {
		public int guess(String word);
	}
}

