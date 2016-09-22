package com.payments.checker;

import com.payments.entity.Checkable;
import java.security.MessageDigest;


public class PasswordChecker {

    public static boolean check(Checkable entity, String password){
        String passwordHash = entity.getPassword();
        String salt = entity.getSalt();
        String saltedPassword = password + salt;
        String saltedPasswordHash = getHash(saltedPassword); 
        boolean result = passwordHash.equals(saltedPasswordHash);
        return result;
    }
    
    
    private static String getHash(String saltedPassword){
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(saltedPassword.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return hash;
    }
    
}
