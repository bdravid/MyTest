package com.dravid.test.uti;

import java.util.*;

/**
 * Created by Bdravid on 15/02/2017.
 */
public class UBSTest3 {
    private static final int ONE_DAY_COST = 2;
    private static final int THIRTY_DAY_COST = 25;
    private static final int SEVEN_DAY_COST = 5;

    public static void main(String[] args) {
        System.out.println(groupsOfN(new int[]{1, 2, 4, 5, 7, 29, 30}, 7));
        System.out.println(groupsOfN(new int[]{1, 2, 4, 5, 7, 29, 30}, 1));
        System.out.println(groupsOfN(new int[]{1, 2, 4, 5, 7, 29, 30}, 30));
    }

    private static int solution(int[] days) {
        List<String> oneDay = groupsOfN(days, 1);
        int index1 = maxConsecutiveDaysIndex(oneDay);
        String[] oneDayers = oneDay.get(index1).split("|");
        int oneDayCost = oneDayers.length * ONE_DAY_COST;
        System.out.println("All one day cost = " + oneDayCost);

        List<String> sevenDay = groupsOfN(days, 7);
        int index7 = maxConsecutiveDaysIndex(sevenDay);
        String[] sevenDayers = sevenDay.get(index7).split("|");
        int sevenDayCost = sevenDayers.length * SEVEN_DAY_COST;
        System.out.println("All seven day cost = " + sevenDayCost);

        List<String> thirtyDay = groupsOfN(days, 30);
        int index30 = maxConsecutiveDaysIndex(thirtyDay);
        String[] thirtyDayers = thirtyDay.get(index30).split("|");
        int thirtyDayCost = thirtyDayers.length * SEVEN_DAY_COST;
        System.out.println("All thirty day cost = " + thirtyDayCost);
        return 0;
    }

    private int max(int a, int b, int c) {
        return 0;
    }

    private static int maxConsecutiveDaysIndex(List<String> oneDay) {
        int indexOfMaxLength=0;
        int oneDayMaxLength = 0;
        for (int i = 0; i < oneDay.size(); i++) {
            String[] splits = oneDay.get(i).split("|");
            if (oneDayMaxLength < splits.length) {
                oneDayMaxLength = splits.length;
                indexOfMaxLength = i;
            }
        }
        return indexOfMaxLength;
    }

    private static List<String> groupsOfN(int[] a, int n) {
        List<String> retval = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            int firstDay = a[i];
            int lastDay = firstDay + n;
            retval.add("");
            for (int j = 0; j < a.length; j++) {
                if (a[j] >= firstDay && a[j] <= lastDay) {
                    if (retval.get(i).equals("")) {
                        retval.set(i, ""+a[j]);
                    } else {
                        retval.set(i, retval.get(i) + "|" + a[j]);
                    }
                }
            }
        }
        return retval;
    }
}
