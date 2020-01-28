package yfdc;
import org.jetbrains.annotations.NotNull;
import java.security.MessageDigest;
import androidx.annotation.Keep;
public final class Jiami {
	@Keep private Jiami() throws java.io.IOException {
		throw (new java.io.IOException("could not be implied"));
	}
	public static String MD5(@NotNull final String password) {
		try {
			// 得到一个信息摘要器
			MessageDigest digester = MessageDigest.getInstance("MD5");
			byte[] result = digester.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
			final StringBuilder buffer = new StringBuilder();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = (((int) b) & 0xFF);// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
			String rtn = buffer.toString();
			android.util.Log.d("PSWD",rtn);
			// 标准的md5加密后的结果
			return rtn;
		} catch (Throwable e) {
			e.printStackTrace(System.out);
			return "<error>";
		}
	}
}
