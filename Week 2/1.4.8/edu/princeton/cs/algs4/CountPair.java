package edu.princeton.cs.algs4;

import java.util.Arrays;

public class CountPair {

    private CountPair() { }
    
    public static int count(int[] a) {
        int n = a.length;
        int count = 0, res = 0;
        Arrays.sort(a);
        for (int i = 1; i < n; i++) {
            if (a[i] == a[i-1]) {
                count++;
            } else {
                res += count * (count + 1) / 2;
                count = 0;
            }
        }
        return res + count * (count + 1) / 2;
    }

    public static void main(String[] args)  {
        In in = new In(args[0]);
        int[] a = in.readAllInts();

        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);
    }
}