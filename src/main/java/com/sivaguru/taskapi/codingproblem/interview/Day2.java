package com.sivaguru.taskapi.codingproblem.interview;

public class Day2 {

  static long validateAndParseTaskId(String input) throws IllegalArgumentException {
    if (input == null || input.isEmpty()) {
      throw new IllegalArgumentException();
    }
      try {
        return Long.parseLong(input);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Not a valid number: " + input);
      }
    }



  public static void main(String[] args) {
   System.out.println(validateAndParseTaskId("5"));
  }

}
