package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/11/2016.
 * #0068 https://leetcode.com/problems/text-justification/
 */
public class TextJustification {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		int n = words.length;
		if(n == 0){
			return res;
		}
		int start = 0, charCount = words[0].length();
		for(int end = 1; end < n; end++){
			int nextWidth = words[end].length() + 1;
			// check if the current word can be append to the current line
			if(charCount + nextWidth > maxWidth){
				res.add(createNewLine(words, start, end - 1, charCount, maxWidth));
				start = end;
				charCount = words[end].length();
			}else{
				charCount += nextWidth;
			}
		}
		// add last line if any words left
		if(charCount > 0){
			res.add(createNewLine(words, start, n - 1, charCount, maxWidth));
		}
		return res;
	}

	private String createNewLine(String[] words, int start, int end, int charCount, int maxWidth){
		boolean leftJustification = (start == end || end == words.length - 1);
		int evenSpace = 0, remainingSpace = 0;
		if(!leftJustification){
			int extraSpace = maxWidth - charCount;
			// calculate number of extra spaces to distribute evenly for each gap
			evenSpace = extraSpace / (end - start);
			// calculate the remaining extra space to fill gap from the left
			remainingSpace = extraSpace % (end - start);
		}
		// add back the one "necessary" space
		String spacing = " ";
		while(evenSpace-- > 0){
			spacing += " ";
		}
		StringBuilder line = new StringBuilder();
		for(int i = start; i <= end; i++){
			line.append(words[i]);
			line.append(i == end ? "" : spacing);
			// append the remaining spaces until exhausted
			line.append(remainingSpace-- > 0 ? " " : "");
		}
		// for left justification, just fill all the spaces to the end
		int spaceToAppend = maxWidth - line.length();
		while(spaceToAppend-- > 0){
			line.append(" ");
		}
		return line.toString();
	}
}
