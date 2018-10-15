/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Ralph
 */
public class PasswordHasher {
    
    public static String passwordHasher(String password){
        String digest = "";
        if (password != null || (!password.isEmpty())) {
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (Exception e) {

            }
            m.update(password.getBytes(), 0, password.length());
            digest = String.format("%032x", new BigInteger(1, m.digest()));

        }
        return digest;
    }
}
