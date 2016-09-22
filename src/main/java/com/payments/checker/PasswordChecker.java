package com.payments.checker;

import com.payments.entity.Checkable;
import java.security.MessageDigest;


public class PasswordChecker {
    
    private MessageDigest messageDigest;
    
    public PasswordChecker(){
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        }        
        catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    public boolean check(Checkable entity, String password){
        String passwordHash = entity.getPassword();
        String salt = entity.getSalt();
        String saltedPassword = password + salt;
        String saltedPasswordHash = getHash(saltedPassword); 
        boolean result = passwordHash.equals(saltedPasswordHash);
        return result;
    }    
    
    private String getHash(String saltedPassword){
        byte[] saltedPasswordBytes = saltedPassword.getBytes();
        messageDigest.update(saltedPasswordBytes);
        byte[] bytes = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hash = stringBuffer.toString();
        return hash;
    }    
}
