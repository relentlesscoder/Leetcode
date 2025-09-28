package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 09/27/2025.
 * #2521 https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/
 */
public class DistinctPrimeFactorsOfProductOfArray {

    // time O(n * sqrt(max) * log(max)), space O(n * sqrt(max) * log(max))
    public int distinctPrimeFactorsShorter(int[] nums) {
        Set<Integer> primeFactors = new HashSet<>();
        for (int number : nums) {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    primeFactors.add(i);
                    while (number % i == 0) {
                        number /= i;
                    }
                }
            }
            // if number still greater than 1，it is a prime number itself
            if (number > 1) {
                primeFactors.add(number);
            }
        }
        return primeFactors.size();
    }

    // time O(n * sqrt(max) * log(max)), space O(n * sqrt(max) * log(max))
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> primeFactors = new HashSet<>();
        for (int number : nums) {

            // Prime Factorization
            // handle even prime factor 2
            while (number % 2 == 0) {
                primeFactors.add(2);
                number /= 2;
            }

            // handle odd prime factor starting from 3
            for (int i = 3; i * i <= number; i += 2) {
                while (number % i == 0) {
                    primeFactors.add(i);
                    number /= i;
                }
            }

            // if number still greater than 1，it is a prime number itself
            if (number > 1) {
                primeFactors.add(number);
            }
        }
        return primeFactors.size();
    }
}
