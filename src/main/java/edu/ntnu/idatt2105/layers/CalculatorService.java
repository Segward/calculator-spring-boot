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

  private Matcher getSegmentMatcher(String equation) {
    Pattern segmentPattern = Pattern.compile("\\d+\\.\\d+|\\d+|[+\\-*/]");
    return segmentPattern.matcher(equation);
  }

  private ArrayList<String> getPatternMatches(Matcher matcher) {
    return Stream.generate(() -> matcher)
        .takeWhile(Matcher::find)
        .map(Matcher::group)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private double computeequation(String equation, String a, String b) {
    switch (equation) {
      case "*":
        return Double.parseDouble(a) * Double.parseDouble(b);
      case "/":
        return Double.parseDouble(a) / Double.parseDouble(b);
      case "+":
        return Double.parseDouble(a) + Double.parseDouble(b);
      case "-":
        return Double.parseDouble(a) - Double.parseDouble(b);
      default:
        throw new IllegalArgumentException("Invalid equation: " + equation);
    }
  }

  private ArrayList<String> computeequation(String equation, ArrayList<String> segments) {
    int index = segments.indexOf(equation);
    double result = computeequation(equation, segments.get(index - 1), segments.get(index + 1));
    segments.set(index - 1, String.valueOf(result));
    segments.remove(index);
    segments.remove(index);
    return segments;
  }

  private ArrayList<String> processequations(ArrayList<String> segments, String equation) {
    return Stream.iterate(
            segments, segs -> segs.contains(equation), segs -> computeequation(equation, segs))
        .reduce((first, second) -> second)
        .orElse(segments);
  }

  private String compute(String equation) {
    ArrayList<String> segments = getPatternMatches(getSegmentMatcher(equation));
    for (String pemdas : pemdasOrder) {
      segments = processequations(segments, pemdas);
    }
    return segments.get(0);
  }

  public static String calculate(String equation) {
    return new CalculatorService().compute(equation);
  }
}