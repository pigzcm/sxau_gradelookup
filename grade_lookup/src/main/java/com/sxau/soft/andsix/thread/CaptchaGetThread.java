package com.sxau.soft.andsix.thread;
import com.sxau.soft.andsix.LoginDialog;
import com.sxau.soft.andsix.MainActivity;
public final class CaptchaGetThread extends java.lang.Thread {
	private LoginDialog dialog;
	public CaptchaGetThread(LoginDialog _dialog) {
		super((Runnable) null, "CaptchaGetThread");
		this.dialog = _dialog;
	}
	@Override public void run() {
		android.os.Message send = null;
		okhttp3.OkHttpClient client = MainActivity.getClient();
		final String _url = (new StringBuilder(MainActivity.CON_URL)).append(MainActivity.getPort()).append("/img/captcha.jpg").toString();
		final okhttp3.Request captchaRequest = (new okhttp3.Request.Builder()).url(_url).build();
		okhttp3.Response captchaResponse = null;
		okhttp3.ResponseBody respbd = null;
		try {
			captchaResponse = client.newCall(captchaRequest).execute();
			respbd = captchaResponse.body();
		} catch (Throwable ex) {
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
			send = (new android.os.Message());
			send.what = 1;
			send.obj = ex;
			dialog.getHandler().sendMessage(send);
			return;
		}
		if (respbd != null) {
			java.io.InputStream stream = respbd.byteStream();
			android.graphics.Bitmap bitmap = android.graphics.BitmapFactory.decodeStream(stream);
			captchaResponse.close();
			send = (new android.os.Message());
			send.what = 0;
			send.obj = bitmap;
			dialog.getHandler().sendMessage(send);
			try {
				stream.close();
			} catch (Throwable h) {
				h.printStackTrace(System.out);
			}
		} else {
			android.util.Log.d("YFDC", "failed to get response stream.");
			send = (new android.os.Message());
			send.what = 1;
			send.obj = "failed to get response stream.";
			dialog.getHandler().sendMessage(send);
		}
	}
}
