package org.wshuai.leetcode.test;

import org.junit.Test;
import org.wshuai.leetcode.RemoveComments;

import java.util.List;

public class RemoveCommentsTest {
	@Test
	public void testcase1(){
		RemoveComments rc = new RemoveComments();
		List<String> res = rc.removeComments(new String[]{"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"});
	}

	@Test
	public void testcase2(){
		RemoveComments rc = new RemoveComments();
		List<String> res = rc.removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"});
	}

	@Test
	public void testcase3(){
		RemoveComments rc = new RemoveComments();
		List<String> res = rc.removeComments(new String[]{"/*/bcbc//*ebdb/*/bab/*/a/*//*/d/*///*de/*///*d*//dc*///*/cd//*ccd//*a//*caacad"});
	}
}
