package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 11/13/2019.
 * #1177 https://leetcode.com/problems/can-make-palindrome-from-substring/
 */
public class CanMakePalindromeFromSubstring {

	// time O(m + n), space O(n)
	public List<Boolean> canMakePaliQueriesBitMask(String s, int[][] queries) {
		// 在下解的基础上继续优化，最多只有 26 个字符所以可以用一个整形来表示各个字符
		// 的数量的奇偶性。
		List<Boolean> res = new ArrayList<>();
		int n = s.length();
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i];
			prefix[i + 1] ^= (1 << (s.charAt(i) - 'a'));
		}
		for (int[] q : queries) {
			int left = q[0], right = q[1], k = q[2];
			int cnt = Integer.bitCount(prefix[right + 1] ^ prefix[left]);
			res.add(cnt / 2 <= k);
		}
		return res;
	}

	// time O(m + n), space O(n)
	public List<Boolean> canMakePaliQueriesXORPrefixSum(String s, int[][] queries) {
		// 与下解同样的思路，不同的是用字符数量前缀和的奇偶性取代数量。根据奇偶性一致相减得偶数
		// 不一致得奇数，可以将所有字符的奇偶性前缀和异或。
		List<Boolean> res = new ArrayList<>();
		int n = s.length();
		int[][] prefix = new int[n + 1][26];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i].clone();
			prefix[i + 1][s.charAt(i) - 'a'] ^= 1; // 计算前缀和的奇偶性
		}
		for (int[] q : queries) {
			int left = q[0], right = q[1], k = q[2], cnt = 0;
			for (int i = 0; i < 26; i++) {
				// 异或前缀和判断子字串中字符数量是否能被 2 整除
				cnt += (prefix[right + 1][i] ^ prefix[left][i]);
			}
			res.add(cnt / 2 <= k);
		}
		return res;
	}

	// time O(m + n), space O(n)
	public List<Boolean> canMakePaliQueriesFrequencyPrefixSum(String s, int[][] queries) {
		// 维护一个字符数量差的前缀和的数组，对每个查询中的范围中的子字串计算它含有的每个
		// 字符的数量。将每个数量对 2 取余 (能够两两匹配的字符不需要替换操作) 然后加总得
		// 到 cnt，需要被替换的字符即为cnt / 2 。注意这里不必单独处理奇偶性，因为 cnt
		// 和整个子字串的奇偶性必然相同。
		List<Boolean> res = new ArrayList<>();
		int n = s.length();
		int[][] prefix = new int[n + 1][26];
		for (int i = 0; i < n; i++) { // O(n)
			prefix[i + 1] = prefix[i].clone(); // O(26)
			prefix[i + 1][s.charAt(i) - 'a']++;
		}
		for (int[] q : queries) { // O(m)
			int left = q[0], right = q[1], k = q[2], cnt = 0;
			for (int i = 0; i < 26; i++) { // O(26)
				cnt += (prefix[right + 1][i] - prefix[left][i]) % 2;
			}
			res.add(cnt / 2 <= k);
		}
		return res;
	}
}
