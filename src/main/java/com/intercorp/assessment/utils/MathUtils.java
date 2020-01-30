package com.intercorp.assessment.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Utils to calculate math operations
 *
 * @author Federico Pereyra
 */
public class MathUtils {

    /**
     * Calculate the average value of ages
     *
     * @param ages
     * @return
     */
    public static int calculateAgeAverage(List<Integer> ages) {
        if (ages.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer age : ages) {
            sum += age;
        }

        return Math.floorDiv(sum, ages.size());
    }

    /**
     * Calculate the standard deviation value of ages
     *
     * @param ages
     * @return
     */
    public static double calculateStandardDeviation(List<Integer> ages) {
        if (ages.isEmpty()) {
            return 0;
        }

        int average = calculateAgeAverage(ages);

        double sumDiffToAverage = 0;
        for (Integer age : ages) {
            sumDiffToAverage += Math.pow(age - average, 2);
        }
        double averageOfDiff = sumDiffToAverage / (double) ages.size();

        return Math.sqrt(averageOfDiff);
    }
}
