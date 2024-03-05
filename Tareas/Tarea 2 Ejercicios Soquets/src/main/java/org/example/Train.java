package org.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Train {

    public static int[] arrayDiff(int[] a , int[] b) {
        // Your code here

        Set<Integer> seta = new HashSet<>();
        Set<Integer> setb = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            seta.add(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            setb.add(b[i]);
        }
        seta.removeAll(setb);

        System.out.println(seta);
        return seta.stream().mapToInt(Integer::intValue).toArray();

}
    public static void main(String[] args) {
        int[] a = {1, 2, 2, 2, 3};
        int[] b = {2};
        System.out.println(arrayDiff(a, b)); // Expected: [1, 3]
    }
}
