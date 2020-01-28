package com.sxau.soft.andsix;
import androidx.annotation.Keep;
public final class Testserv extends android.app.Service{
	private static final String TAG = "TestServ";
	@Keep @Override public android.os.IBinder onBind(android.content.Intent nul){
		android.util.Log.d(TAG,"override method_1 does nothing " + String.valueOf(nul));
		return null;
	}
	public Testserv(){
		super();
		android.util.Log.d(TAG,"service constructor called");
	}
	@Override public int onStartCommand(android.content.Intent intent, int flags, int startId) {
		int call = super.onStartCommand(intent, flags, startId);
		(new com.sxau.soft.andsix.thread.TestThread()).start();
		return call;
	}
}
