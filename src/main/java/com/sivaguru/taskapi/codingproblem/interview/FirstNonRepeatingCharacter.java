package com.sivaguru.taskapi.codingproblem.interview;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

  public static char firstNonRepeatingCharacter(String str) {
    Map<Character, Integer> counts = new HashMap<>();

    for (char c : str.toCharArray()) {
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }

    for (char c : str.toCharArray()) {
      if (counts.get(c) == 1) {
        return c;
      }
    }
    return '?';
  }

  public static void main(String[] args) {

    System.out.println(firstNonRepeatingCharacter("swiss"));
    System.out.println(firstNonRepeatingCharacter("aabb"));

  }

}
