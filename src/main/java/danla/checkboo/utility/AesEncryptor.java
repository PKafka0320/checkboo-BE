package danla.checkboo.utility;

import static danla.checkboo.common.exception.errorCode.CommonErrorCode.FAIL_DECRYPT;
import static danla.checkboo.common.exception.errorCode.CommonErrorCode.FAIL_ENCRYPT;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import danla.checkboo.common.exception.CheckBooException;

public class AesEncryptor {

	private static final String ALGORITHM = "AES";
	private final SecretKeySpec secretKey;

	public AesEncryptor(String key) {
		this.secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	}

	public String encrypt(String strToEncrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes()));
		} catch (Exception e) {
			throw new CheckBooException(FAIL_ENCRYPT);
		}
	}

	public String decrypt(String strToDecrypt) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			throw new CheckBooException(FAIL_DECRYPT);
		}
	}
}
