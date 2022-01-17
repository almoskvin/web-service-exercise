package com.github.almoskvin.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
class MathUtilsTest {

    private static Stream<Arguments> argumentSourceProviderMin() {
        return Stream.of(
                Arguments.of(1,
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{1}),
                Arguments.of(1,
                        new int[]{14, 2, 1, 4, 5},
                        new int[]{1}),
                Arguments.of(1,
                        new int[]{100, 2, 3, 4, 0},
                        new int[]{0}),
                Arguments.of(5,
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{1, 2, 3, 4, 5}),
                Arguments.of(2,
                        new int[]{1, 2, 3, 4, 0},
                        new int[]{0, 1})
        );
    }

    private static Stream<Arguments> argumentSourceProviderMax() {
        return Stream.of(
                Arguments.of(1,
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{5}),
                Arguments.of(1,
                        new int[]{1, 2, 13, 4, 5},
                        new int[]{13}),
                Arguments.of(1,
                        new int[]{100, 2, 3, 4, 5},
                        new int[]{100}),
                Arguments.of(5,
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{1, 2, 3, 4, 5}),
                Arguments.of(2,
                        new int[]{1, 2, 3, 4, 0},
                        new int[]{3, 4})
        );
    }

    private static Stream<Arguments> argumentSourceProviderAvg() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4},
                        3),
                Arguments.of(
                        new int[]{1, 2, 13, 4, 5},
                        5),
                Arguments.of(
                        new int[]{100, 2, 3, 4, 5},
                        22.8)
        );
    }

    private static Stream<Arguments> argumentSourceProviderMedian() {
        return Stream.of(
                Arguments.of(
                        new int[]{2, 4},
                        3),
                Arguments.of(
                        new int[]{1, 2, 13, 4, 5},
                        13),
                Arguments.of(
                        new int[]{100, 2, 3, 4, 5, 10},
                        3.5)
        );
    }

    private static Stream<Arguments> argumentSourceProviderPercentile() {
        return Stream.of(
                Arguments.of(
                        5,
                        new int[]{15, 20, 35, 40, 50},
                        15),
                Arguments.of(
                        50,
                        new int[]{15, 20, 35, 40, 50},
                        35),

                Arguments.of(
                        75,
                        new int[]{3, 6, 7, 8, 8, 10, 13, 15, 16, 20},
                        15),
                Arguments.of(
                        100,
                        new int[]{3, 6, 7, 8, 8, 10, 13, 15, 16, 20},
                        20),

                Arguments.of(
                        25,
                        new int[]{3, 6, 7, 8, 8, 9, 10, 13, 15, 16, 20},
                        7),
                Arguments.of(
                        75,
                        new int[]{3, 6, 7, 8, 8, 9, 10, 13, 15, 16, 20},
                        15)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentSourceProviderMin")
    void getMin(int quantifier, int[] numbers, int[] expected) {
        int[] result = MathUtils.getMin(quantifier, numbers);
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("argumentSourceProviderMax")
    void getMax(int quantifier, int[] numbers, int[] expected) {
        int[] result = MathUtils.getMax(quantifier, numbers);
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("argumentSourceProviderAvg")
    void getMax(int[] numbers, double expected) {
        double result = MathUtils.getAvg(numbers);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("argumentSourceProviderMedian")
    void getMedian(int[] numbers, double expected) {
        double result = MathUtils.getMedian(numbers);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("argumentSourceProviderPercentile")
    void getPercentile(int quantifier, int[] numbers, int expected) {
        int result = MathUtils.getPercentile(quantifier, numbers);
        Assertions.assertEquals(expected, result);
    }
}