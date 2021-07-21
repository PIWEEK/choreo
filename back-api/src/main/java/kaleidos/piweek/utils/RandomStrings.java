package kaleidos.piweek.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStrings {
  private static char[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  
  public static String randomString(int length) {
    Random random = new SecureRandom();
    char[] result = new char[length];
    for (int i = 0; i < result.length; i++) {
      // picks a random index out of character set > random character
      int randomCharIndex = random.nextInt(characterSet.length);
      result[i] = characterSet[randomCharIndex];
    }
    return new String(result);
  }
  
}
