package husky.wooof.com.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
	public static String encode(String toencode) {

		if (toencode.matches("[0-9a-f]{32}")) {
			return toencode;
		}

		byte[] uniqueKey = toencode.getBytes();
		byte[] hash = null;

		try {
			hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
		} catch (NoSuchAlgorithmException e) {
			throw new Error("no MD5 support in this VM");

		}
		StringBuffer hashString = new StringBuffer();

		for (int i = 0; i < hash.length; ++i) {

			String hex = Integer.toHexString(hash[i]);
			if (hex.length() == 1) {
				hashString.append('0');
				hashString.append(hex.charAt(hex.length() - 1));
			}
			else {
				hashString.append(hex.substring(hex.length() - 2));
			}

		}

		return hashString.toString();
	}
}