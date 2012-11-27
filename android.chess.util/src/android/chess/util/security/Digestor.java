package android.chess.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digestor {
	/**
	 * 
	 */
	public static final String ALGORITHM = "MD5";

	/**
	 * @param texto
	 * @return
	 * 
	 * @see http://m2tec.be/blog/2010/02/03/java-md5-hex-0093
	 */
	public String digerir(String texto) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
			byte[] array = md.digest(texto.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static class Holder {

		public static final Digestor INSTANCIA = new Digestor();

	}

	/**
	 * @return
	 */
	public static Digestor instancia() {
		return Holder.INSTANCIA;
	}
}
