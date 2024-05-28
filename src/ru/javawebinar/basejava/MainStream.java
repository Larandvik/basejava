package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainStream {
    public static void main(String[] args) {
        int minValue = minValue(new int[]{1, 2, 3, 3, 2, 3});
        System.out.println(minValue);

        minValue = minValue(new int[]{9, 8});
        System.out.println(minValue);

        List<Integer> odd = oddOrEven(List.of(1,2,3,1));
        List<Integer> even = oddOrEven(List.of(1,2,3));
        System.out.println(odd);
        System.out.println(even);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sumOdds = 0;
        int sumEvens = 0;
        int sum = 0;
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();

        for (int i = 0; i < integers.size(); i++) {
            int value = integers.get(i);
            if (value % 2 == 0) {
                sumEvens += value;
                evens.add(value);
            } else {
                sumOdds += value;
                odds.add(value);
            }
            sum += value;
        }
        if (sum %2 == 0) {
            return evens;
        } else return odds;
    }

    private static int minValue(int[] ints) {
        return Arrays.stream(ints)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }
}
