package com.coolgame;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class FairKey {
    public SecretKey getKey() throws Exception {
        SecureRandom secureRand = new SecureRandom();
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        keyGen.init(256, secureRand);

        SecretKey secretKey = keyGen.generateKey();
    
        return secretKey;
    }
    public void showKey(SecretKey secretKey) {
        byte[] keyBin = secretKey.getEncoded();
        char key[] = new char[keyBin.length * 2];
        for (int i = 0; i < keyBin.length; i++) {
            key[i * 2] = "0123456789abcdef".charAt((keyBin[i] >> 4) & 15);
            key[i * 2 + 1] = "0123456789abcdef".charAt(keyBin[i] & 15);
        }
        System.out.println("HMAC Key (Base16/Hex):\n" + new String(key));
    }
}
