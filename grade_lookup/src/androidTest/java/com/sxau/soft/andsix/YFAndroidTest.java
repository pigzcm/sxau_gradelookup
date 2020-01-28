package com.sxau.soft.andsix;
import org.junit.Test;
import org.junit.runner.RunWith;
import yfdc.Circle;
import yfdc.Rectangle;
@RunWith(value = androidx.test.ext.junit.runners.AndroidJUnit4.class)
public final class YFAndroidTest {
	public YFAndroidTest() {
		super();
		android.util.Log.d("YFDC", "Test Constructor called");
	}
	@Test public void useAppContest() {
		android.content.Context arg1 = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().getTargetContext();
		try {
			org.junit.Assert.assertEquals("com.sxau.soft.andseven", arg1.getPackageName());
		} catch (Throwable ex) {
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
		}
		Class<yfdc.GsonFactory> abc = yfdc.GsonFactory.class;
		yfdc.GsonFactory fac = null;
		try {
			fac = abc.newInstance();
		} catch (Throwable ex) {
			yfdc.GsonFactory.logstacktrace(yfdc.GsonFactory.stacktrace(ex));
		}
		if (fac != null) {
			android.util.Log.d("Test", fac.toString());
		}
		Circle crl = (new Circle.Builder()).radius(2.0).build();
		Circle cr1 = (new Circle.Builder(5.0)).build();
		Rectangle rcl = (new Rectangle.Builder()).width(3.5).height(1.5).build();
		Rectangle rc1 = (new Rectangle.Builder(5)).height(7).build();
		android.util.Log.d("YFDC:crl", crl.toString());
		android.util.Log.d("YFDC:cr1", cr1.toString());
		android.util.Log.d("YFDC:rcl", rcl.toString());
		android.util.Log.d("YFDC:rc1", rc1.toString());
	}
}
