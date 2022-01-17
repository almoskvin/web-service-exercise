package com.github.almoskvin.utils;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MathUtils {


    private static class ComputationException extends RuntimeException {
    }

    public static int[] getMin(int quantifier, int[] numbers) {
        return Arrays.stream(numbers)
                .sorted()
                .limit(quantifier)
                .toArray();
    }

    public static int[] getMax(int quantifier, int[] numbers) {
        return Arrays.stream(numbers)
                .sorted()
                .skip(numbers.length - quantifier)
                .toArray();
    }

    public static double getAvg(int[] numbers) {
        return Arrays.stream(numbers)
                .average()
                .orElseThrow(ComputationException::new);
    }

    public static double getMedian(int[] numbers) {
        int length = numbers.length;
        if (length % 2 == 1) {
            return numbers[length / 2];
        }
        return (double) (numbers[length / 2] + numbers[length / 2 - 1]) / 2;
    }

    /**
     * Get the percentile using the nearest rank method.
     *
     * @param quantifier percentile quantifier
     * @param numbers    array of data
     * @return percentile value
     */
    public static int getPercentile(int quantifier, int[] numbers) {
        int[] sortedNumbers = Arrays.stream(numbers)
                .sorted()
                .toArray();
        int index = (int) Math.ceil((double) quantifier / 100 * numbers.length);
        return sortedNumbers[index - 1];
    }

}
