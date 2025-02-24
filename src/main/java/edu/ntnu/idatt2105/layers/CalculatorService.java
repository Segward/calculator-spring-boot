package edu.ntnu.idatt2105.layers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

  private Matcher getSegmentMatcher(String equation) {
    Pattern segmentPattern = Pattern.compile("-?\\d+\\.\\d+|-?\\d+|[\\-*/]");
    return segmentPattern.matcher(equation);
  }

  private ArrayList<String> getPatternMatches(Matcher matcher) {
    return Stream.generate(() -> matcher)
        .takeWhile(Matcher::find)
        .map(Matcher::group)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private ArrayList<String> computePemdasMultiplications(ArrayList<String> segments) {
    if (segments.contains("*")) {
      int index = segments.indexOf("*");
      double result =
          Double.parseDouble(segments.get(index - 1)) * Double.parseDouble(segments.get(index + 1));
      segments.set(index - 1, String.valueOf(result));
      segments.remove(index);
      segments.remove(index);
      return computePemdasMultiplications(segments);
    } else {
      return segments;
    }
  }

  private ArrayList<String> computePemdasDivisions(ArrayList<String> segments) {
    if (segments.contains("/")) {
      int index = segments.indexOf("/");
      double result =
          Double.parseDouble(segments.get(index - 1)) / Double.parseDouble(segments.get(index + 1));
      segments.set(index - 1, String.valueOf(result));
      segments.remove(index);
      segments.remove(index);
      return computePemdasDivisions(segments);
    } else {
      return segments;
    }
  }

  private String compute(String equation) {
    ArrayList<String> segments = getPatternMatches(getSegmentMatcher(equation));
    segments = computePemdasDivisions(segments);
    segments = computePemdasMultiplications(segments);
    double result = 0;
    for (String segment : segments) {
      result += Double.parseDouble(segment);
    }
    BigDecimal rounded = new BigDecimal(result).setScale(5, RoundingMode.HALF_UP);
    return rounded.stripTrailingZeros().toPlainString();
  }

  public ResultModel calculate(String equation) {
    String result = new CalculatorService().compute(equation);
    return new ResultModel(equation, result);
  }
}