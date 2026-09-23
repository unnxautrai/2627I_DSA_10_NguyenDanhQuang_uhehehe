package edu.princeton.cs.algs4;

import java.util.Arrays;

public class FindPair {

    public static void main(String[] args) {
        In in = new In(args[0]);
        double[] a = in.readAllDoubles(); 

        if (a.length < 2) {
            StdOut.println("Khong co cap nao");
            return;
        }

        Arrays.sort(a);
        int n = a.length;
        double f1 = a[0], f2 = a[n - 1];
        double c1 = a[0], c2 = a[1];
        for (int i = 1; i < n - 1; i++) {
            if (a[i + 1] - a[i] > f2 - f1) {
                f1 = a[i];
                f2 = a[i + 1];
            }
        }
        StdOut.println("Cap gan nhau nhat: " + c1 + " va " + c2 + " (Khoang cach: " + (c2 - c1) + ")");
        StdOut.println("Cap xa nhau nhat:  " + f1 + " va " + f2 + " (Khoang cach: " + (f2 - f1) + ")");
    }
}