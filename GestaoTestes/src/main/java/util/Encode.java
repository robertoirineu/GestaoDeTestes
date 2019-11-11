package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encode {
	private static MessageDigest md = null;

	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	private static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;

		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public static String encrip(String pwd) {
		if (md != null) {
			return new String(hexCodes(md.digest(pwd.getBytes())));
		}
		return null;
	}

	private int contador = 0;
	private int tamanho = 0;
	private int codigoASCII = 0;
	private String senhaCriptografada = "";

	public String encript(String mensagem) {
		if (mensagem == null || mensagem.equals("")) {
			return "";
		}

		tamanho = mensagem.length();
		mensagem = mensagem.toUpperCase();
		while (contador < tamanho) {
			codigoASCII = mensagem.charAt(contador) + 130;
			senhaCriptografada = senhaCriptografada + (char) codigoASCII;
			contador++;
		}
		return Base64.getEncoder().encodeToString(senhaCriptografada.getBytes());
	}

	public String decrip(String mensagemCriptografada) {

		if (mensagemCriptografada == null || mensagemCriptografada.equals("")) {
			return "";
		}

		tamanho = mensagemCriptografada.length();
		mensagemCriptografada = mensagemCriptografada.toUpperCase();
		while (contador < tamanho) {
			codigoASCII = mensagemCriptografada.charAt(contador) - 130;
			senhaCriptografada = senhaCriptografada + (char) codigoASCII;
			contador++;
		}
		return new String(Base64.getDecoder().decode(mensagemCriptografada));
	}
}
