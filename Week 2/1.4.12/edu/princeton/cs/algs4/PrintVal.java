package edu.princeton.cs.algs4;

import java.util.Arrays;
import java.util.ArrayList;

public class PrintVal {

    public static void print(int[] a, int[] b) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                    list.add(a[i]);
                }
                i++;
            } else {
                if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                    list.add(b[j]);
                }
                j++;
            }
        }
        while (i < a.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != a[i]) {
                list.add(a[i]);
            }
            i++;
        }
        while (j < b.length) {
            if (list.isEmpty() || list.get(list.size() - 1) != b[j]) {
                list.add(b[j]);
            }
            j++;
        }
        for (int k = 0; k < list.size(); k++) {
            StdOut.print(list.get(k));
            if (k < list.size() - 1) {
                StdOut.print(" ");
            }
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] allInts = in.readAllInts(); // Đọc linh hoạt toàn bộ file

        int n = allInts.length / 2;
        int[] a1 = new int[n];
        int[] a2 = new int[allInts.length - n];

        System.arraycopy(allInts, 0, a1, 0, n);
        System.arraycopy(allInts, n, a2, 0, a2.length);
        print(a1, a2);
    }
}