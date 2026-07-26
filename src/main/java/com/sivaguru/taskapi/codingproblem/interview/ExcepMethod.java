package com.sivaguru.taskapi.codingproblem.interview;

public class ExcepMethod {

  public static Long validateId(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id is null");
    } else if (id <= 0) {
      throw new IllegalArgumentException("Id not valid: " + id);
    }
    return id;
  }

  public static void main(String[] args) {
    test(null);
    test(-5L);
    test(0L);
    test(7L);

    test1(null);

    System.out.println(safeParse("42"));
    System.out.println(safeParse("abc"));
    System.out.println(safeParse(""));
    System.out.println(safeParse(null));
  }

  static void test(Long input) {
    try {
      System.out.println("OK: returned " + validateId(input));
    } catch (IllegalArgumentException e) {
      System.out.println("THREW: " + e.getMessage());
    }
  }

  static void test1(Long input) {
    try {
      throw new InvalidStatusException("Status must be TODO, IN_PROGRESS or DONE");
    } catch (InvalidStatusException e) {
      System.out.println("caught: " + e.getMessage());
    }
  }

  public static int safeParse(String s) {
    try {
      return Integer.parseInt(s);          // the risky attempt
    } catch (NumberFormatException e) {
      return -1;                    // the promise kept anyway
    }
  }
}
