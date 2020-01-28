package com.sxau.soft.andsix;
public final class Defapp extends android.app.Application {
	static {
		System.loadLibrary("yflib");
	}
	public Defapp() {
		super();
		android.util.Log.d("YFDC", "Application contest constructor called.");
	}
	@Override public void onCreate() {
		Class<?> cls1 = null;
		Class<?> cls2 = null;
		super.onCreate();
		android.util.Log.d("YFDC", "onCreate() called on application contest.");
		try {
			cls1 = Class.forName("yfdc.CookieJarImplKt");
			cls2 = Class.forName("yfdc.GsonFactory");
		} catch (Throwable h) {
			h.printStackTrace(System.out);
			cls1 = yfdc.CookieJarImplKt.class;
			cls2 = yfdc.GsonFactory.class;
		} finally {
			if ((cls1 != null) && (cls2 != null)) {
				android.util.Log.d("YFDC", cls1.toString());
				android.util.Log.d("YFDC", cls2.toString());
			}
		}
	}
	@Override public void onTerminate() {
		super.onTerminate();
		System.exit(0);
		throw (new RuntimeException("Application exited successfully"));
	}
}
