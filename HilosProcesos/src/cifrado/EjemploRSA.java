package cifrado;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
//RSA (Rivest, Shamir y Adleman) es un sistema criptográfico de clave pública desarrollado en 1977.
//Es el primer y más utilizado algoritmo de este tipo y es válido tanto para cifrar como para firmar digitalmente.

public class EjemploRSA {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		KeyPair kp = keyGen.genKeyPair();
		PublicKey publicKey = kp.getPublic();
		PrivateKey privateKey = kp.getPrivate();
		String text = "Clase del sabado";
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] x = cipher.doFinal(text.getBytes());
		System.out.println("Mensaje - " + text + " - cifrado:");
		for (int i = 0; i < x.length - 1; i++) {
			System.out.print(x[i]);
		}
		System.out.println("------------------------------");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] y = cipher.doFinal(x);
		System.out.println(" Mensaje descifrado:");
		for (int i = 0; i < y.length - 1; i++) {
			System.out.print(y[i]);
		}
	}
}