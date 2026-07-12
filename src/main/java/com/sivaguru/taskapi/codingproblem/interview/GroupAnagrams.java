package com.sivaguru.taskapi.codingproblem.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

  public static Map<String, List<String>> groupAnagrams(List<String> words) {

    Map<String, List<String>> groups = new HashMap<>();

    for (String word : words) {
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      String signature = new String(chars);

      if (!groups.containsKey(signature)) {
        groups.put(signature, new ArrayList<>());
      }

      groups.get(signature).add(word);
    }

    return groups;
  }

  public static void main(String[] args) {
    List<String> words = List.of("listen", "silent", "enlist", "task", "west", "stew");
    System.out.println(groupAnagrams(words));

  }

}
