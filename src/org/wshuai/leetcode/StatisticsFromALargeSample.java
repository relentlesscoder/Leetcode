package org.wshuai.leetcode;

/**
 * Created by Wei on 11/13/2019.
 * #1093 https://leetcode.com/problems/statistics-from-a-large-sample/
 */
public class StatisticsFromALargeSample {
	// https://www.dictionary.com/e/mean-median-mode/
	// https://leetcode.com/problems/statistics-from-a-large-sample/discuss/317857/Java-Simple-2-pass-code-w-comments-and-explanation.
	public double[] sampleStats(int[] count) {
		int total = 0;
		int mode = 0;
		double max = 0;
		double min = -1;
		double sum = 0;
		double mean = 0;
		double median = 0;
		for(int i = 0; i < 256; i++){
			if(count[i] > 0){
				total += count[i];
				if(min < 0){
					min = i;
				}
				max = i;
				sum += i * count[i];
				if(count[i] > count[mode]){
					mode = i;
				}
			}
		}
		mean = sum / total;
		if(total == 1){
			median = sum;
		}
		int m1 = (total + 1) / 2;
		int m2 = total /2 + 1;
		for(int i = 0, cnt = 0; total > 1 && i < 256; i++){
			if(cnt < m1 && cnt + count[i] >= m1){
				median += i / 2.0d;
			}
			if(cnt < m2 && cnt + count[i] >= m2){
				median += i / 2.0d;
			}
			cnt += count[i];
		}
		return new double[]{min, max, mean, median, mode};
	}
}
