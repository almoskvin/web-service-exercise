package com.github.almoskvin.controller;

import com.github.almoskvin.utils.MathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Controller
@RequestMapping("api/v1/math")
@Slf4j
public class MathController {

    @GetMapping("min/{quantifier}/{numbers}")
    @ResponseBody
    public int[] getMin(@PathVariable("quantifier") int quantifier, @PathVariable("numbers") int[] numbers) {
        log.info("New getMin request: quantifier {}, numbers {}", quantifier, Arrays.toString(numbers));
        try {
            if (quantifier <= 0 || numbers.length < quantifier) {
                log.error("Quantifer {} is less than 1 or greater than the data array list length", quantifier);
                throw new IllegalArgumentException("Passed noncompliant arguments");
            }
            int[] min = MathUtils.getMin(quantifier, numbers);
            log.info("Calculated value {}", Arrays.toString(min));
            return min;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("max/{quantifier}/{numbers}")
    @ResponseBody
    public int[] getMax(@PathVariable("quantifier") int quantifier, @PathVariable("numbers") int[] numbers) {
        log.info("New getMax request: quantifier {}, numbers {}", quantifier, Arrays.toString(numbers));
        try {
            if (quantifier <= 0 || numbers.length < quantifier) {
                log.error("Quantifer {} is less than 1 or greater than the data array list length", quantifier);
                throw new IllegalArgumentException("Passed noncompliant arguments");
            }
            int[] max = MathUtils.getMax(quantifier, numbers);
            log.info("Calculated value {}", Arrays.toString(max));
            return max;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("avg/{numbers}")
    @ResponseBody
    public double getAverage(@PathVariable("numbers") int[] numbers) {
        log.info("New getAverage request: numbers {}", Arrays.toString(numbers));
        try {
            double avg = MathUtils.getAvg(numbers);
            log.info("Calculated value {}", avg);
            return avg;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("median/{numbers}")
    @ResponseBody
    public double getMedian(@PathVariable("numbers") int[] numbers) {
        log.info("New getMedian request: numbers {}", Arrays.toString(numbers));
        try {
            double median = MathUtils.getMedian(numbers);
            log.info("Calculated value {}", median);
            return median;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }

    @GetMapping("percentile/{quantifier}/{numbers}")
    @ResponseBody
    public int getPercentile(@PathVariable("quantifier") int quantifier, @PathVariable("numbers") int[] numbers) {
        log.info("New getPercentile request: quantifier {}, numbers {}", quantifier, Arrays.toString(numbers));
        try {
            if (quantifier <= 0 || quantifier > 100) {
                log.error("Quantifer is not within range [1..100]");
                throw new IllegalArgumentException("Passed noncompliant arguments");
            }
            int percentile = MathUtils.getPercentile(quantifier, numbers);
            log.info("Calculated value {}", percentile);
            return percentile;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
        }
    }
}
