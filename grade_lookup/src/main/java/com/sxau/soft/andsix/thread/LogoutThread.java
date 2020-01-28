package com.sxau.soft.andsix.thread;
import com.sxau.soft.andsix.MainActivity;
public final class LogoutThread extends java.lang.Thread {
	public LogoutThread() {
		super((Runnable) null, "LogoutThread");
	}
	@Override public void run() {
		nativerun0();
		okhttp3.OkHttpClient client = MainActivity.getClient();
		final String _url = (MainActivity.CON_URL + MainActivity.getPort() + "/logout");
		okhttp3.Request req = (new okhttp3.Request.Builder()).url(_url).build();
		okhttp3.Response resp = null;
		try {
			resp = client.newCall(req).execute();
			resp.close();
			resp = null;
		} catch (Throwable h) {
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(h));
		}
		yfdc.CookieJarImplKt.clearCache();
	}
	private native void nativerun0();
}
