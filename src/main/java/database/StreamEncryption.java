package database;

import exceptions.logger.CostumeLogger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class StreamEncryption {



    private static final String transformation = "AES/ECB/PKCS5Padding";

    public static void encrypt(byte[] key,Serializable object, OutputStream ostream) throws IOException,  InvalidKeyException {
        try {
            SecretKeySpec sks = new SecretKeySpec(key, transformation);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(sealedObject);
            outputStream.close();
        } catch (IllegalBlockSizeException | NoSuchPaddingException|NoSuchAlgorithmException e) {
            CostumeLogger.getInstance().logError(e);
            throw new IOException();
        }
    }

    public static Object decrypt(byte[] key,InputStream istream) throws IOException, InvalidKeyException {
        SecretKeySpec sks = new SecretKeySpec(key, transformation);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException|NoSuchPaddingException e) {
            CostumeLogger.getInstance().logError(e);
            throw new IOException();
        }
        cipher.init(Cipher.DECRYPT_MODE, sks);

        CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
        SealedObject sealedObject;
        try {
            sealedObject = (SealedObject) inputStream.readObject();
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            CostumeLogger.getInstance().logError(e);
            throw new IOException();
        }
    }
}
