package kaleidos.piweek.utils;

import java.security.SecureRandom;
import java.util.Random;

public class RandomStrings {
  private static char[] alphaNumericSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
  private static char[] alphabeticSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private static char[] numericSet = "0123456789".toCharArray();
  
  public static String getPinCode() {
    Random random = new SecureRandom();
    char[] pinCode = new char[5];
    for (int i = 0; i < pinCode.length; i++) {
      // picks a random index out of character set > random character
      if (i == 0) {
        int randomAlphaIndex = random.nextInt(alphabeticSet.length);
        pinCode[i] = alphabeticSet[randomAlphaIndex];
      } else {
        int randomNumIndex = random.nextInt(numericSet.length);
        pinCode[i] = numericSet[randomNumIndex];
      }
    }
    return new String(pinCode);
  }
  
  public static String randomString(int length) {
    Random random = new SecureRandom();
    char[] result = new char[length];
    for (int i = 0; i < result.length; i++) {
      // picks a random index out of character set > random character
      int randomCharIndex = random.nextInt(alphaNumericSet.length);
      result[i] = alphaNumericSet[randomCharIndex];
    }
    return new String(result);
  }
  
}
