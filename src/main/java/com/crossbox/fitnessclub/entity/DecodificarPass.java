
package com.crossbox.fitnessclub.entity;

import java.util.Random;


public class DecodificarPass {
  
     private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  
  public static String generatePassword(int length) {
    StringBuilder password = new StringBuilder();
    Random random = new Random();
    
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(CHARACTERS.length());
      password.append(CHARACTERS.charAt(index));
    }
    
    return password.toString();
  }
}
