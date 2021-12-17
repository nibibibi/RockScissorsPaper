package com.coolgame;

import javax.crypto.Mac;
import javax.crypto.SecretKey;

public class FairHMAC {
    public byte[] getHmac(SecretKey key, byte[] message) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] hmac = mac.doFinal(message);
        return hmac;
    }
    public void showHmac(byte[] hmac) {
        char finalHmac[] = new char[hmac.length * 2];
        for (int i = 0; i < hmac.length; i++) {
            finalHmac[i * 2] = "0123456789abcdef".charAt((hmac[i] >> 4) & 15);
            finalHmac[i * 2 + 1] = "0123456789abcdef".charAt(hmac[i] & 15);
        } 
        System.out.println("HMAC:\n" + new String(finalHmac));
    }
}
