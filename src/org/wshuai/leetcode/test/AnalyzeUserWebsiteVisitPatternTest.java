package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.AnalyzeUserWebsiteVisitPattern;

import java.util.List;

public class AnalyzeUserWebsiteVisitPatternTest {
	@Test
	public void testcase(){
		AnalyzeUserWebsiteVisitPattern auw = new AnalyzeUserWebsiteVisitPattern();
		List<String> res = auw.mostVisitedPattern(
			new String[]{"h","eiy","cq","h","cq","txldsscx","cq","txldsscx","h","cq","cq"},
			new int[]{527896567,334462937,517687281,134127993,859112386,159548699,51100299,444082139,926837079,317455832,411747930},
			new String[]{"hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","hibympufi","yljmntrclw","hibympufi","yljmntrclw"});
	}
}
