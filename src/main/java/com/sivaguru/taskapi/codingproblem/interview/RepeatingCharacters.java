package com.sivaguru.taskapi.codingproblem.interview;

import java.util.HashMap;
import java.util.Map;

public class RepeatingCharacters {

  public static Map<Character, Integer> repeatingCharacters(String str) {
    Map<Character, Integer> map = new  HashMap<>();
     for (char c : str.toCharArray()) {
       map.put(c, map.getOrDefault(c, 0) + 1);

     }
//       if (map.containsKey(c)) {
//         map.put(c, map.get(c)+1);
//       } else {
//         map.put(c, 1);
//       }
//     }
     return map;
  }

  public static void main(String[] args) {

    System.out.println(repeatingCharacters("PENDING"));

  }

}
