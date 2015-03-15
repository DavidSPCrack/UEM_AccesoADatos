package cifrado;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class CifrarDescifrar {
	private static Cipher encryptCipher;
	private static Cipher decryptCipher;

	public static void main(String[] args) {
		try {
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			SecretKey secretKey = keygenerator.generateKey();
			encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			// Texto que se va a Cifrar
			byte[] encryptedData = encryptData(" !! Información Clasificada !!");
			decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
			decryptData(encryptedData);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	// Se cifra el Texto
	private static byte[] encryptData(String data) throws IllegalBlockSizeException, BadPaddingException {
		System.out.println("Datos antes del Cifrado: " + data);
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = encryptCipher.doFinal(dataToEncrypt);
		System.out.println("Datos Cifrados - Encryted Data: " + encryptedData);
		return encryptedData;
	}

	// Se descifra el Texto
	private static void decryptData(byte[] data) throws IllegalBlockSizeException, BadPaddingException {
		byte[] textDecrypted = decryptCipher.doFinal(data);
		System.out.println("Datos Descifrados - Decryted Data: " + new String(textDecrypted));
	}
}