package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainStream {
    public static void main(String[] args) {
//        int minValue = minValue(new int[]{1, 2, 3, 3, 2, 3});
//        System.out.println(minValue);
//
//        minValue = minValue(new int[]{9, 8});
//        System.out.println(minValue);
//
//        List<Integer> even = oddOrEven(List.of(1, 2, 3, 1));
//        List<Integer> odd = oddOrEven(List.of(1, 2, 3));
//        System.out.println(odd);
//        System.out.println(even);
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> ints = integers.stream()
                .collect(Collectors.partitioningBy(integer -> integer % 2 == 0));
        return ints.get(false).size() % 2 == 0 ? ints.get(false) : ints.get(true);
    }

    private static int minValue(int[] ints) {
        return Arrays.stream(ints)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }
}
