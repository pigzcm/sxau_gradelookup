package com.sxau.soft.andsix.thread;
import com.sxau.soft.andsix.LoginDialog;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public final class LoginThread extends Thread {
	private LoginDialog dialog = null;
	private String username = null;
	private String password = null;
	private String capt_code = null;
	public LoginThread(LoginDialog _dialog, String _username, String _password, String _capt_code) {
		super((Runnable) null, "LoginThread");
		this.dialog = _dialog;
		this.username = _username;
		this.password = _password;
		this.capt_code = _capt_code;
	}
	@Override public void run() {
		final okhttp3.FormBody form = (new okhttp3.FormBody.Builder())
				.add("j_username", this.username)
				.add("j_password", this.password)
				.add("j_captcha", this.capt_code).build();
		final okhttp3.Request loginRequest = (new okhttp3.Request.Builder())
				.url(com.sxau.soft.andsix.MainActivity.CON_URL + com.sxau.soft.andsix.MainActivity.getPort() + "/j_spring_security_check")
				.post(form)
				.build();
		final okhttp3.OkHttpClient client = com.sxau.soft.andsix.MainActivity.getClient();
		okhttp3.Response response = null;
		okhttp3.ResponseBody respbd = null;
		try {
			response = client.newCall(loginRequest).execute();
			respbd = response.body();
			if (respbd != null) {
				if (response.request().url().toString().endsWith("index.jsp")) {
					String str = LoginThread.getName(respbd.string());
					response.close();
					android.os.Message msg = new android.os.Message();
					msg.what = 2;
					msg.obj = str;
					dialog.getHandler().sendMessage(msg);
					return;
				} else {
					String url = response.request().url().toString();
					String begin = "errorCode=";
					int i = url.indexOf(begin);
					final String reason = url.substring(i + begin.length(), url.length());
					android.os.Message msg = new android.os.Message();
					response.close();
					msg.what = 3;
					msg.obj = reason;
					dialog.getHandler().sendMessage(msg);
					return;
				}
			} else {
				android.util.Log.d("YFDC", "empty response body");
				throw new NullPointerException("Empty response body.");
			}
		} catch (Throwable ex) {
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
			android.os.Message msg = new android.os.Message();
			msg.what = 4;
			msg.obj = "网络连接失败";
			dialog.getHandler().sendMessage(msg);
		}
	}
	@NotNull private static String getName(@Nullable final String html) {
		if (html == null || html.length() == 0) {
			return "";
		}
		String begin = "<small>欢迎您，</small>";
		int a = html.indexOf(begin);
		String end = "</span>";
		int b = html.indexOf(end, a);
		return html.substring(a + begin.length(), b).trim();
	}
}
