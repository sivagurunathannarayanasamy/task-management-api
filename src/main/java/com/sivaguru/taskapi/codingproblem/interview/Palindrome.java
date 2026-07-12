package com.sivaguru.taskapi.codingproblem.interview;

import java.util.ArrayList;

public class Palindrome {

  public static boolean isPalindrome(String str) {

    str = str.toLowerCase();

    int left = 0;
    int right = str.length() - 1;

    while(left < right) {
      if (str.charAt(left) != str.charAt(right)) {
        return false;
      }
      left++;
      right--;
    }
    return true;
  }

  public static void main(String[] args) {

    System.out.println(isPalindrome("level"));
    System.out.println(isPalindrome("task"));

  }

}
