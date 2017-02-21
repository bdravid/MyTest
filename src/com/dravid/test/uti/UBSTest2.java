package com.dravid.test.uti;

import java.util.Arrays;

/**
 * Created by Bdravid on 15/02/2017.
 */
public class UBSTest2 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 5, 3, 3, 7}));
        System.out.println(solution(new int[]{1, 5, 6, 7, 7}));
        System.out.println(solution(new int[]{1, 3, 5, 3, 4}));
        System.out.println(solution(new int[]{1, 5, 3}));


    }

    private static boolean solution(int[] A) {
        if (isSorted(A)) {
            return true;
        }
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
            swapInPlace(i, j, A);
                if (isSorted(A)) {
                    return true;
                } else {
                    swapInPlace(j, i, A);
                    continue;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int[] a) {
        return a.length > 0;
    }

    private static void swapInPlace(int first, int second, int[] arr) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void swapInPlace2(int first, int second, int[] arr) {
        arr[first] = arr[first] + arr[second];
        arr[second] = arr[first] - arr[second];
        arr[first] = arr[first] - arr[second];
    }

    private static boolean isSorted(int[] A) {
        for (int i = 0; i < A.length-1; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
