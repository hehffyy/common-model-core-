package com.butone.license;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {

	public static final String ALGORITHM_DSA = "DSA";
	private static final int RSA_DECODE_SIZE = 128;

	/**
	 * 读取DSA公钥
	 * 
	 * @param file
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static PublicKey readDSAPublicKey(byte[] buff)
			throws InvalidKeySpecException, NoSuchAlgorithmException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buff);
		// ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_DSA);
		// 取公钥匙对象
		return keyFactory.generatePublic(keySpec);

	}

	/**
	 * DSA验证
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
	public static boolean verifyFile(String dataFile, String signFile,
			PublicKey key) {
		FileInputStream verifyFile = null;
		try {
			byte[] signData = new byte[(int) new File(signFile).length()];
			// 读取签名文件
			FileInputStream inSign = new FileInputStream(signFile);
			try {
				if (inSign.read(signData) != signData.length) {
					return false;
				}
			} finally {
				inSign.close();
			}
			Signature signature = Signature.getInstance("DSA");
			signature.initVerify(key);
			// 读取jar
			byte[] buff = new byte[32768];
			int len = -1;
			verifyFile = new FileInputStream(dataFile);
			while ((len = verifyFile.read(buff)) != -1) {
				signature.update(buff, 0, len);
			}
			return signature.verify(signData);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (verifyFile != null)
				try {
					verifyFile.close();
				} catch (IOException e1) {
				}
		}
	}

	private static Cipher Cipher_RSA;
	private static final String java_security_Provider = "org.bouncycastle.jce.provider.BouncyCastleProvider";

	private static Cipher getRSACipher() throws Exception {
		if (Cipher_RSA == null)
			Cipher_RSA = Cipher.getInstance("RSA/ECB/PKCS1Padding",
					(Provider)Class.forName(java_security_Provider).newInstance());
		return Cipher_RSA;
	}

	/**
	 * RSA解密，每次RSA_DECODE_SIZE字节进行解密
	 * 
	 * @param key
	 * @param sourceData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	public static byte[] rsaDecrypt(Key key, byte[] sourceData)
			throws Exception {
		return rsaDecrypt(key, sourceData, 0, sourceData.length);
	}

	/**
	 * RSA解密，每次RSA_DECODE_SIZE字节进行解密
	 * 
	 * @param key
	 * @param sourceData
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws IOException
	 */
	private static byte[] rsaDecrypt(Key key, byte[] sourceData, int offset,
			int length) throws Exception {
		Cipher cipher = getRSACipher();
		cipher.init(Cipher.DECRYPT_MODE, key);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			int rLen = Math.min(length - offset, RSA_DECODE_SIZE);
			while (offset < length) {
				try {
					out.write(cipher.doFinal(sourceData, offset, 128));
					offset += rLen;
					rLen = Math.min(length - offset, RSA_DECODE_SIZE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			out.flush();
			return out.toByteArray();
		} finally {
			try {
				out.close();
			} catch (IOException e) {

			}
		}
	}

	/**
	 * 生成密钥
	 * 
	 * @param keyData
	 *            Key的Encoded数据
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	public static PublicKey readRSAPublicKey(byte[] keyData)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec rsaKeySpec = new X509EncodedKeySpec(keyData);
		return kf.generatePublic(rsaKeySpec);
	}

	/**
	 * AES解密
	 * 
	 * @param password
	 * @param cryptograph
	 * @param offset
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static byte[] aesDecrypt(byte[] password, byte[] cryptograph,
			int offset, int length) throws Exception {
		Cipher localCipher = getAESCipher();
		localCipher.init(Cipher.DECRYPT_MODE,
				new SecretKeySpec(password, "AES"));
		return localCipher.doFinal(cryptograph, offset, length);
	}

	private static Cipher Cipher_AES = null;

	private static Cipher getAESCipher() throws NoSuchAlgorithmException,
			NoSuchPaddingException {
		if (Cipher_AES == null) {
			Cipher_AES = Cipher.getInstance("AES");
		}
		return Cipher_AES;
	}

	// ********************************Base64编码处理**************************
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', '+', '/' };

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
			60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
			-1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
			38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
			-1, -1 };

	public static String base64Encode(byte[] data) {
		StringBuffer sb = new StringBuffer();
		int len = data.length;
		int i = 0;
		int b1, b2, b3;

		while (i < len) {
			b1 = data[i++] & 0xff;
			try {
				if (i == len) {
					sb.append(base64EncodeChars[b1 >>> 2]);
					sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
					sb.append("==");
					break;
				}
				b2 = data[i++] & 0xff;
				if (i == len) {
					sb.append(base64EncodeChars[b1 >>> 2]);
					sb.append(base64EncodeChars[((b1 & 0x03) << 4)
							| ((b2 & 0xf0) >>> 4)]);
					sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
					sb.append("=");
					break;
				}
				b3 = data[i++] & 0xff;
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4)
						| ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[((b2 & 0x0f) << 2)
						| ((b3 & 0xc0) >>> 6)]);
				sb.append(base64EncodeChars[b3 & 0x3f]);
			} catch (Exception e) {
			}

		}
		return sb.toString();
	}

	public static byte[] base64Decode(String str) {
		byte[] data = str.getBytes();
		return base64Decode(data);
	}

	public static byte[] base64Decode(byte[] data) {
		int len = data.length;
		ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
		int i = 0;
		int b1, b2, b3, b4;

		while (i < len) {
			/* b1 */
			do {
				b1 = base64DecodeChars[data[i++]];
			} while (i < len && b1 == -1);
			if (b1 == -1)
				break;

			/* b2 */
			do {
				b2 = base64DecodeChars[data[i++]];
			} while (i < len && b2 == -1);
			if (b2 == -1)
				break;
			buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

			/* b3 */
			do {
				b3 = data[i++];
				if (b3 == 61)
					return buf.toByteArray();
				b3 = base64DecodeChars[b3];
			} while (i < len && b3 == -1);
			if (b3 == -1)
				break;
			buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

			/* b4 */
			do {
				b4 = data[i++];
				if (b4 == 61)
					return buf.toByteArray();
				b4 = base64DecodeChars[b4];
			} while (i < len && b4 == -1);
			if (b4 == -1)
				break;
			buf.write((int) (((b3 & 0x03) << 6) | b4));
		}
		return buf.toByteArray();
	}

	private static MessageDigest messageDigest;

	private static MessageDigest getMD5MessageDigest()
			throws NoSuchAlgorithmException {
		if (messageDigest == null)
			messageDigest = MessageDigest.getInstance("MD5");
		return messageDigest;
	}

	/**
	 * md5加密
	 * 
	 * @param paramString
	 * @return
	 */
	public static String MD5(String paramString) {
		if (paramString == null)
			return null;
		try {
			StringBuffer localStringBuffer = new StringBuffer();
			for (byte b : getMD5MessageDigest().digest(paramString.getBytes()))
				localStringBuffer.append(String.format("%02x",
						new Object[] { Byte.valueOf(b) }));
			return localStringBuffer.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Md5加密失败:", e);
		}
	}

}
