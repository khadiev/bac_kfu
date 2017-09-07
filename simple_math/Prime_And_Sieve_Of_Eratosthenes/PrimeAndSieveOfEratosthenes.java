import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Khadiev on 07.09.2017.
 * Problem 1: Check is number a prime
 * Solution 1: check all numbers 2..sqrt(a) as divisor
 *
 * Problem 2: Find all primes in segment [a,b]
 * Algorithm 2: Sieve of Eratosthenes
 * Solution 2: if i is prime then we mark all numbers j>i such that j%i=0. Number j jumps with step i.
 */
public class PrimeAndSieveOfEratosthenes {
    //T=O(sqrt(a)) S=O(1)
    boolean isPrime(int a){
        boolean result = false;
        for (int i = 1; i <= Math.sqrt(a)+1; i++){
            if (a%i ==0){
                result = false;
                break;
            }
        }
        return result;
    }

    //T=O(nloglogn), S=O(max(a,b))
    List<Integer> getPrimesInSegment(int a, int b){
        if (a>b){
            int x = a;
            a = b;
            b = x;
        }
        int n = b+1;
        boolean[] np = new boolean[n];
        for (int i = 2; i < n; i++){
            if (!np[i])
                for (int j = 2*i; j<n; j+=i)
                    np[j] = true;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = a; i < b+1; i++){
            if (!np[i])
                result.add(i);
        }
        return result;
    }
}
