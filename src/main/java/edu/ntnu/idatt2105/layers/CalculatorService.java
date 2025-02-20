package edu.ntnu.idatt2105.layers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
  private final String[] pemdasOrder = {"/", "*", "+", "-"};

  private Matcher getSegmentMatcher(String operation) {
    Pattern segmentPattern = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]");
    return segmentPattern.matcher(operation);
  }

  private ArrayList<String> getPatternMatches(Matcher matcher) {
    return Stream.generate(() -> matcher)
        .takeWhile(Matcher::find)
        .map(Matcher::group)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private double computeOperation(String operation, String a, String b) {
    switch (operation) {
      case "*":
        return Double.parseDouble(a) * Double.parseDouble(b);
      case "/":
        return Double.parseDouble(a) / Double.parseDouble(b);
      case "+":
        return Double.parseDouble(a) + Double.parseDouble(b);
      case "-":
        return Double.parseDouble(a) - Double.parseDouble(b);
      default:
        throw new IllegalArgumentException("Invalid operation: " + operation);
    }
  }

  private ArrayList<String> computeOperation(String operation, ArrayList<String> segments) {
    int index = segments.indexOf(operation);
    double result = computeOperation(operation, segments.get(index - 1), segments.get(index + 1));
    segments.set(index - 1, String.valueOf(result));
    segments.remove(index);
    segments.remove(index);
    return segments;
  }

  private ArrayList<String> processOperations(ArrayList<String> segments, String operation) {
    return Stream.iterate(
            segments, segs -> segs.contains(operation), segs -> computeOperation(operation, segs))
        .reduce((first, second) -> second)
        .orElse(segments);
  }

  private String compute(String operation) {
    ArrayList<String> segments = getPatternMatches(getSegmentMatcher(operation));
    for (String pemdas : pemdasOrder) {
      segments = processOperations(segments, pemdas);
    }
    return segments.get(0);
  }

  public static String calculate(String operation) {
    return new CalculatorService().compute(operation);
  }
}