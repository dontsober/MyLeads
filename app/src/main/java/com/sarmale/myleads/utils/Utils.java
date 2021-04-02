package com.sarmale.myleads.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Utils {
    public byte[] getIdFirebaseFromEmail(String email) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] emailToBytes = email.getBytes();
        byte[] emailMD5 = messageDigest.digest(emailToBytes);
        return emailMD5;
    }

}
