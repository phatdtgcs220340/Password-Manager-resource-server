package com.phatdo.resource_server.Configuration.Security.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SaltGenerator {

    public static byte[] getSaltFromString(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes());
    }
}

