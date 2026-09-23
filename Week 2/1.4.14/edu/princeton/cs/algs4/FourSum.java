package edu.princeton.cs.algs4;

import java.util.Arrays;
import java.util.ArrayList;

public class FourSum {

    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        Arrays.sort(a);
        if (n < 4) return 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int r = n - 1, target = -a[i] - a[j];
                for (int l = 0; l < i; l++) {
                    while (r > j && a[l] + a[r] > target) {
                        r--;
                    } 
                    if (r <= j) break;
                    if (a[l] + a[r] == target) {
                        int cntl = 1, cntr = 1;
                        while (l + 1 < i && a[l] == a[l + 1]) {
                            cntl++;
                            l++;
                        }
                        while (r - 1 > j && a[r] == a[r - 1]) {
                            cntr++;
                            r--;
                        }
                        count += cntl * cntr;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] allInts = in.readAllInts(); 
        StdOut.println(FourSum.count(allInts));
    }
}