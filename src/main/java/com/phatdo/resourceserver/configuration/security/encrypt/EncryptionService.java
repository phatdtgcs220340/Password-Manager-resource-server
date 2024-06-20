package com.phatdo.resourceserver.configuration.security.encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class EncryptionService {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    private static final int IV_LENGTH = 12; // 96 bits
    private final SecretProperties secretProperties;

    @Autowired
    public EncryptionService(SecretProperties secretProperties) {
        this.secretProperties = secretProperties;
    }

    public String encrypt(String data) throws Exception {
        String saltString = secretProperties.getSalt();
        String passphrase = secretProperties.getPassphrase();
        byte[] salt = SaltGenerator.getSaltFromString(saltString);
        SecretKey key = KeyDerivation.deriveKey(passphrase.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        byte[] encryptedDataWithIv = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, encryptedDataWithIv, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, encryptedDataWithIv, iv.length, encryptedBytes.length);

        return Base64.getEncoder().encodeToString(encryptedDataWithIv);
    }

    public String decrypt(String encryptedData) throws Exception {

        String saltString = secretProperties.getSalt();
        String passphrase = secretProperties.getPassphrase();
        byte[] encryptedDataWithIv = Base64.getDecoder().decode(encryptedData);
        byte[] salt = SaltGenerator.getSaltFromString(saltString);
        SecretKey key = KeyDerivation.deriveKey(passphrase.toCharArray(), salt);

        byte[] iv = new byte[IV_LENGTH];
        byte[] encryptedBytes = new byte[encryptedDataWithIv.length - iv.length];

        System.arraycopy(encryptedDataWithIv, 0, iv, 0, iv.length);
        System.arraycopy(encryptedDataWithIv, iv.length, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
