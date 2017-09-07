/**
 * Created by Kamil Khadiev on 07.09.2017.
 * Greatest Common Divisor and Least Common Multiple
 * Problem: WE should find gcd(a,b) and lcm(a,b)
 * Algorithm: Euclid algorithm
 */

public class GcdLcm {
    //T=O(max(a,b)) S=O(1)
    int gcd1(int a, int b){
        while (b!=0){
            if (a<b) {
                int x = a;
                a = b;
                b = x;
            }
            int r = a-b;
            b = a;
            a = r;
        }
        return a;
    }
    //T=O(log(max(a,b))) S=O(1)
    int gcd2(int a, int b){
        while (b!=0){
            int r = a%b;
            b = a;
            a = r;
        }
        return a;
    }
    //T=T_gcd S=S_gcd+O(1)
    int lcm(int a,int b){
        return a*b/gcd2(a,b);
    }
    //T=O(log(max(a,b))) S=O(1)
    int lcm2(int a,int b){
        int a1 = a;
        int b1 = b;
        while (b!=0) {
            int r = a % b;
            b = a;
            a = r;
        }
        return a1*b1/a;
    }
}
