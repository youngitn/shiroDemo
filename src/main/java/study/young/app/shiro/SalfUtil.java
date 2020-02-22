package study.young.app.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SalfUtil {

	public static final String md5(String password, String salt) {
		// 加密方式
		String hashAlgorithmName = "MD5";
		// 鹽：爲了即使相同的密碼不同的鹽加密後的結果也不同
		ByteSource byteSalt = ByteSource.Util.bytes(salt);
		// 密碼
		Object source = password;
		// 加密次數
		int hashIterations = 2;
		SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
		return result.toString();
	}

	public static void main(String[] args) {
		String str = md5("123", "wx");
		System.out.print(str);
	}
}
